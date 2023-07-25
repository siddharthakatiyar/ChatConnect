package com.chatconnect.backend.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chatconnect.backend.models.Message;
import com.chatconnect.backend.models.MessageRead;
import com.chatconnect.backend.repository.MessageRepository;
import com.chatconnect.backend.repository.UserRepository;
import com.chatconnect.backend.security.jwt.JwtUtils;
import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.HandshakeData;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

import io.jsonwebtoken.JwtException;
import io.netty.handler.codec.http.HttpHeaders;

@Component
public class SocketIOController {

    private static final Logger log = LoggerFactory.getLogger(SocketIOController.class);

    private final SocketIOServer socketServer;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    public SocketIOController(SocketIOServer server) {
        this.socketServer = server;
        this.socketServer.addConnectListener(onConnected);
        this.socketServer.addDisconnectListener(onDisconnected());
        this.socketServer.addEventListener("send_message", Message.class, onMessageReceived);        
    }

    private DataListener<Message> onMessageReceived = new DataListener<Message>() {
        @Override
        public void onData(SocketIOClient client, Message message, AckRequest acknowledge) throws Exception {
            log.debug("Client[{}] - Received chat message: {}", client.getSessionId().toString(), message);

            String token = client.getHandshakeData().getSingleUrlParam("token");
            if (token != null) {
                try {
                    if (jwtUtils.validateJwtToken(token)) {
                        String senderUsername = jwtUtils.getUsernameFromJwtToken(token);
                        message.setSender_username(senderUsername);

                        if (senderUsername == null || senderUsername.isEmpty()) {
                            log.debug("Client[{}] - Invalid token format!", client.getSessionId().toString());
                            return;
                        }
        
                        if (!senderUsername.equals(message.getSender_username())) {
                            log.debug("Client[{}] - Token username does not match message sender_username!", client.getSessionId().toString());
                            return;
                        }

                        if (!userRepository.existsByUsername(message.getReceiver_username())) {
                            log.debug("Client[{}] - Username does not exist!", client.getSessionId().toString());
                            return;
                        }

                        messageRepository.save(message);

                        MessageRead messageRead = new MessageRead(message, message.getSender_username());

                        String code = message.getReceiver_username() + "." + senderUsername;
                        socketServer.getRoomOperations(code).sendEvent("receive_message", messageRead);
                    }
                } catch (JwtException e) {
                    log.error("Client[{}] - Invalid token: {}", client.getSessionId().toString(), e.getMessage());
                }
            } else {
                log.error("Client[{}] - Invalid or missing token.", client.getSessionId().toString());
            }
        }
    };

    private ConnectListener onConnected = new ConnectListener() {
        @Override
        public void onConnect(SocketIOClient client) {
            HttpHeaders headers = client.getHandshakeData().getHttpHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
            headers.add("Access-Control-Allow-Headers", "Authorization, Content-Type");
            HandshakeData handshakeData = client.getHandshakeData();
            log.debug("Client[{}] - Connected to chat module through '{}'", client.getSessionId().toString(), handshakeData.getUrl());
            String roomCode = client.getHandshakeData().getSingleUrlParam("code");
            String token = client.getHandshakeData().getSingleUrlParam("token");
            if (token != null) {
                try {
                    if (jwtUtils.validateJwtToken(token)) {
                        String username = jwtUtils.getUsernameFromJwtToken(token);
                        if (username == null || username.isEmpty()) {
                            log.debug("Client[{}] - Invalid token format!", client.getSessionId().toString());
                            return;
                        }

                        roomCode = username + "." + roomCode;

                        client.joinRoom(roomCode);
                    }
                } catch (JwtException e) {
                    log.error("Client[{}] - Invalid token: {}", client.getSessionId().toString(), e.getMessage());
                }
            } else {
                log.error("Client[{}] - Invalid or missing token.", client.getSessionId().toString());
            }
        }
    };

    private DisconnectListener onDisconnected() {
        return client -> {
            log.debug("Client[{}] - Disconnected from chat module.", client.getSessionId().toString());
        };
    }
}

package com.chatconnect.backend.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.chatconnect.backend.models.Message;
import com.chatconnect.backend.payload.requests.MessageRequest;
import com.chatconnect.backend.repository.MessageRepository;
import com.chatconnect.backend.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CCWebSocketHandler extends TextWebSocketHandler {

    private final Map<String, WebSocketSession> userSessionMap = new HashMap<>();

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = (String) session.getAttributes().get("userId");

        if (userId != null) {
            userSessionMap.put(userId, session);
        } else {
            session.close();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Called when a message is received from the client

        // Get the user ID associated with the WebSocket session
        String userId = getUserIdFromSession(session);

        if (userId != null) {
            String receivedMessage = message.getPayload();

            // Convert the received JSON message back to a Java object (using Jackson
            // ObjectMapper)
            ObjectMapper objectMapper = new ObjectMapper();
            MessageRequest messageRequest = objectMapper.readValue(receivedMessage, MessageRequest.class);

            // Extract the receiver ID and the actual message content from the
            // MessageRequest
            String receiverId = messageRequest.getReceiverUsername();
            String messageContent = messageRequest.getMessage();
            String time = messageRequest.getTime();

            // Save the message to the database
            messageRepository.save(new Message(messageContent, userId, receiverId, time));


            // Process the message and send the response (if needed)
            // For example, you can log the message, validate its format, or perform any
            // other actions

            // // Send a response back to the sender (optional)
            // String response = "Received your message: " + messageContent;
            // sendMessageToUser(userId, response);

            // Send a private message to the specified user (using receiverId)
            sendMessageToUser(receiverId, userId, messageContent, time);
        } else {
            // If the user is not authenticated (no associated user ID)
            // You can choose how to handle unauthenticated messages
            // For example, you can ignore the message or send an error response
            // Here, we close the WebSocket session for unauthenticated messages
            session.close();
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Called when a WebSocket connection is closed

        // Remove the WebSocket session from the userSessionMap
        String userId = getUserIdFromSession(session);
        if (userId != null) {
            userSessionMap.remove(userId);
        }
    }

    private String getUserIdFromSession(WebSocketSession session) {
        // Get the user ID associated with the WebSocket session from the userSessionMap
        return userSessionMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(session))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    // For sending a message to a specific user:
    private void sendMessageToUser(String userId, String senderUsername, String message, String time) throws IOException {
        WebSocketSession session = userSessionMap.get(userId);
        if (session != null && session.isOpen()) {
            // Create a JSON object to hold the message details
            MessageRequest messagePayload = new MessageRequest(message, time, senderUsername);
            ObjectMapper objectMapper = new ObjectMapper();
            String messageJson = objectMapper.writeValueAsString(messagePayload);

            // Send the JSON message via the WebSocket
            session.sendMessage(new TextMessage(messageJson));
        } else {
            // Handle the case when the specified user is not connected or their WebSocket session is closed
            // You can log this event or take appropriate actions based on your application's requirements
        }
    }
    

}

package com.chatconnect.backend.payload;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Code to execute when a new WebSocket connection is established
        System.out.println("New WebSocket connection established: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Code to handle incoming WebSocket text messages
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);
        
        // Echo the received message back to the client
        session.sendMessage(new TextMessage("Echo: " + payload));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Code to execute when a WebSocket connection is closed
        System.out.println("WebSocket connection closed: " + session.getId());
    }
}

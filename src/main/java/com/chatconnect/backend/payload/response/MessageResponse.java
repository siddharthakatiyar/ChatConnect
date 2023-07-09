package com.chatconnect.backend.payload.response;

public class MessageResponse {
    private String message;

    public MessageResponse(String newMessage) {
        this.message = newMessage;
    }

    public String getMessage() {
        return this.message;
    }

    public String setMessage(String newMessage) {
        return this.message = newMessage;
    }
}

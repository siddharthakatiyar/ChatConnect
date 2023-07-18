package com.chatconnect.backend.payload.requests;

import jakarta.validation.constraints.NotBlank;

public class MessageRequest {
    @NotBlank
    private String message;

    @NotBlank
    private String time;

    // @NotBlank
    private String receiverUsername;

    private String senderUsername;

    public MessageRequest() {
    }

    public MessageRequest(String message, String time, String senderUsername) {
        this.message = message;
        this.time = time;
        this.senderUsername = senderUsername;
    }

    public String getMessage() {
        return this.message;
    }

    public String getReceiverUsername() {
        return this.receiverUsername;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    public void setReceiverUsername(String newreceiverUsername) {
        this.receiverUsername = newreceiverUsername;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String newSenderUsername) {
        this.senderUsername = newSenderUsername;
    }
}

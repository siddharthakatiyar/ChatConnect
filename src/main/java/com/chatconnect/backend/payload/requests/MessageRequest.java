package com.chatconnect.backend.payload.requests;

import jakarta.validation.constraints.NotBlank;

public class MessageRequest {
    @NotBlank
    private String message;

    @NotBlank
    private String time;

    private long receiver_id;

    public String getMessage() {
        return this.message;
    }

    public long getReceiver_id() {
        return this.receiver_id;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    public void setReceiver_id(long newReceiver_id) {
        this.receiver_id = newReceiver_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

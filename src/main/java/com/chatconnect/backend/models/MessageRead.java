package com.chatconnect.backend.models;

import jakarta.persistence.Entity;

public class MessageRead {
    private String message;
    private String me;
    private String created_at;

    public MessageRead() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public MessageRead(Message message, String me) {
        this.message = message.getMessage();
        this.me = message.getSender_username() == me ? "true" : "false";
        this.created_at = message.getCreated_at();
    }
}

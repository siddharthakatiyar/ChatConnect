package com.chatconnect.backend.models;

public class MessageRead {
    private String message;
    private Boolean me;
    private String created_at;

    public MessageRead() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public MessageRead(Message message, String me) {
        this.message = message.getMessage();
        this.created_at = message.getCreated_at();
        this.me = message.getSender_username().equals(me);
    }

    public Boolean getMe() {
        return me;
    }

    public void setMe(Boolean me) {
        this.me = me;
    }
}

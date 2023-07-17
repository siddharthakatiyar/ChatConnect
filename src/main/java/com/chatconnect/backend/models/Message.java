package com.chatconnect.backend.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long message_id;

    @Column(name = "message")
    private String message;

    @Column(name = "sender_username")
    private String sender_username;

    @Column(name = "receiver_username")
    private String receiver_username;

    @Column(name = "created_at")
    private String created_at;

    public Message() {

    }

    public Message(String message, String sender_username, String receiver_username, String created_at) {
        this.message = message;
        this.sender_username = sender_username;
        this.receiver_username = receiver_username;
        this.created_at = created_at;
    }

    public long getMessage_id() {
        return message_id;
    }

    public String getMessage() {
        return message;
    }

    public String getSender_username() {
        return sender_username;
    }

    public String getReceiver_username() {
        return receiver_username;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender_username(String sender_username) {
        this.sender_username = sender_username;
    }

    public void setReceiver_username(String receiver_username) {
        this.receiver_username = receiver_username;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String newCreated_at) {
        this.created_at = newCreated_at;
    }
}

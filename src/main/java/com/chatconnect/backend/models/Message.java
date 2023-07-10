package com.chatconnect.backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long message_id;

    @Column(name = "message")
    private String message;

    @Column(name = "sender_id")
    private long sender_id;

    @Column(name = "receiver_id")
    private long receiver_id;

    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Message() {

    }

    public Message(String message, long sender_id, long receiver_id, LocalDateTime created_at) {
        this.message = message;
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.created_at = created_at;
    }

    public long getMessage_id() {
        return message_id;
    }

    public String getMessage() {
        return message;
    }

    public long getSender_id() {
        return sender_id;
    }

    public long getReceiver_id() {
        return receiver_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSender_id(long sender_id) {
        this.sender_id = sender_id;
    }

    public void setReceiver_id(long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

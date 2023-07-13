package com.chatconnect.backend.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.chatconnect.backend.repository.UserRepository;

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
    private LocalDateTime created_at;

    // @Autowired
    // private UserRepository userRepository;

    public Message() {

    }

    public Message(String message, String sender_username, String receiver_username) {
        this.message = message;
        this.sender_username = sender_username;
        this.receiver_username = receiver_username;
        this.created_at = LocalDateTime.now();
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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}

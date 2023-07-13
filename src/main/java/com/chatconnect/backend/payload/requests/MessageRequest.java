package com.chatconnect.backend.payload.requests;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.chatconnect.backend.security.services.UserDetailsGet;

import jakarta.validation.constraints.NotBlank;

public class MessageRequest {
    @NotBlank
    private String message;

    // @NotBlank
    private long receiver_id;

    public String getMessage() {
        return this.message;
    }

    // public long getSender_id() {
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     UserDetailsGet userDetails = (UserDetailsGet) authentication.getPrincipal();
    //     return userDetails.getUser_id();
    // }

    public long getReceiver_id() {
        return this.receiver_id;
    }

    public void setMessage(String newMessage) {
        this.message = newMessage;
    }

    // public void setSender_id(long newSender_id) {
    //     this.sender_id = newSender_id;
    // }

    public void setReceiver_id(long newReceiver_id) {
        this.receiver_id = newReceiver_id;
    }
}

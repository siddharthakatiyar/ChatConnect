package com.chatconnect.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatconnect.backend.models.Message;
import com.chatconnect.backend.payload.requests.MessageRequest;
import com.chatconnect.backend.payload.response.MessageResponse;
import com.chatconnect.backend.repository.MessageRepository;
import com.chatconnect.backend.repository.UserRepository;
import com.chatconnect.backend.security.services.UserDetailsGet;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/post/{username}")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageRequest messageRequest, @PathVariable String username) {
        Message message = new Message(messageRequest.getMessage(), getCurrentUsername(), username);
        messageRepository.save(message);

        return ResponseEntity.ok(new MessageResponse("Message sent successfully!"));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getMessages(@PathVariable String username) {
        List<Message> messages = messageRepository.findMessages(getCurrentUsername(), username);
        return ResponseEntity.ok(messages);
    }   

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsGet userDetails = (UserDetailsGet) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}
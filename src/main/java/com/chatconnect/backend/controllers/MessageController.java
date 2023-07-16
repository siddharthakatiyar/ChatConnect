package com.chatconnect.backend.controllers;

import java.util.ArrayList;
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
import com.chatconnect.backend.models.MessageRead;
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
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username does not exist!"));
        }
        Message message = new Message(messageRequest.getMessage(), getCurrentUsername(), username, messageRequest.getTime());
        messageRepository.save(message);

        return ResponseEntity.ok(new MessageResponse("Message sent successfully!"));
    }

    @GetMapping("/get/{username}")
    public ResponseEntity<?> getMessages(@PathVariable String username) {
        if (!userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username does not exist!"));
        }
        List<Message> messages = messageRepository.findMessages(getCurrentUsername(), username);
        List<MessageRead> messageRead = new ArrayList<MessageRead>();
        for (Message message : messages) {
            messageRead.add(new MessageRead(message, getCurrentUsername()));
        }
        return ResponseEntity.ok(messageRead);
    } 
    
    @GetMapping("/get/recent")
    public ResponseEntity<?> getRecentContacts() {
        List<String> recentContacts = messageRepository.findRecentContacts(getCurrentUsername());
        // if (recentContacts.isEmpty()) {
        //     return ResponseEntity.badRequest().body(new MessageResponse("Error: No recent contacts!"));
        // }
        return ResponseEntity.ok(recentContacts);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsGet userDetails = (UserDetailsGet) authentication.getPrincipal();
        return userDetails.getUsername();
    }
}

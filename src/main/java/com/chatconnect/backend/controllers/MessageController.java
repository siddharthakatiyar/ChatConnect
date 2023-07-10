package com.chatconnect.backend.controllers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody MessageRequest messageRequest) {
        Message message = new Message(messageRequest.getMessage(), messageRequest.getSender_id(), messageRequest.getReceiver_id(), LocalDateTime.now());
        messageRepository.save(message);

        return ResponseEntity.ok(new MessageResponse("Message sent successfully!"));
    }

    @GetMapping("/get/{id}/{id2}")
    public Optional<Message> getMessages(@PathVariable long id, @PathVariable long id2) {
        return messageRepository.findBySender_idAndReceiver_id(id, id2);
    }

    // @GetMapping("/get")
    // public List<Message> getMessages() {
    //     return messageRepository.findAll();
    // }


    
}

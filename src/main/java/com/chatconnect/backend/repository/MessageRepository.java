package com.chatconnect.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.chatconnect.backend.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m WHERE (m.sender_username = ?1 AND m.receiver_username = ?2) OR (m.sender_username = ?2 AND m.receiver_username = ?1) ORDER BY m.created_at ASC")
    List<Message> findMessages(String sender_username, String receiver_username);  
}

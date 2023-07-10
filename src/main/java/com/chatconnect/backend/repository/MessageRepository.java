package com.chatconnect.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatconnect.backend.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findBySender_idAndReceiver_id(long sender_id, long receiver_id);
    Boolean existsBySender_idAndReceiver_id(long sender_id, long receiver_id);
}

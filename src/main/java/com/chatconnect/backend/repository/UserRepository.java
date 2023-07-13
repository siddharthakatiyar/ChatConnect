package com.chatconnect.backend.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.chatconnect.backend.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
}

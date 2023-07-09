package com.chatconnect.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatconnect.backend.models.Role;
import com.chatconnect.backend.models.UserType;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(UserType name);
}

package com.chatconnect.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatconnect.backend.models.JwtBlacklist;

@Repository 
public interface JwtRepository extends JpaRepository<JwtBlacklist, Long> {
   
    boolean existsByJwt(String jwt);
}

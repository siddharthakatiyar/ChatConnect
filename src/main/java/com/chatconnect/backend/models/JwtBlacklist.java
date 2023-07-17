package com.chatconnect.backend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jwt_blacklist")
public class JwtBlacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long jwt_id;

    @Column(name = "jwt")
    private String jwt;

    public JwtBlacklist() {

    }

    public JwtBlacklist(String jwt) {
        this.jwt = jwt;
    }

    public long getJwt_id() {
        return jwt_id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt_id(long jwt_id) {
        this.jwt_id = jwt_id;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}

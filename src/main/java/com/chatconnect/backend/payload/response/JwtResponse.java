package com.chatconnect.backend.payload.response;

import java.time.LocalDateTime;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private Long validity;
    // private List<String> roles;

    public JwtResponse(String newToken, Long newId, String newUsername) {
        this.token = newToken;
        this.id = newId;
        this.username = newUsername;
        this.validity = 1L * 60 * 60 * 24;
        // this.roles = newRoles;
    }

    public String getToken() {
        return this.token;
    }

    public String setToken(String newToken) {
        return this.token = newToken;
    }

    public String getType() {
        return this.type;
    }

    public String setType(String newType) {
        return this.type = newType;
    }

    public Long getId() {
        return this.id;
    }

    public Long setId(Long newId) {
        return this.id = newId;
    }

    public String getUsername() {
        return this.username;
    }

    public String setUsername(String newUsername) {
        return this.username = newUsername;
    }

    public Long getValidity() {
        return this.validity;
    }

    // public List<String> getRoles() {
    //     return this.roles;
    // }

    // public List<String> setRoles(List<String> newRoles) {
    //     return this.roles = newRoles;
    // }
}

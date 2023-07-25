package com.chatconnect.backend.models;

public class UserRead {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRead() {

    }

    public UserRead(String username) {
        this.username = username;
    }
}

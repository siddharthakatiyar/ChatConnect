package com.chatconnect.backend.payload.requests;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 8, max = 40)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    // private Set<String> role;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    // public Set<String> getRole() {
    //     return this.role;
    // }

    public void setUsername(String newUsername) {
        this.username = newUsername;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    // public void setRole(Set<String> newRole) {
    //     this.role = newRole;
    // }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}

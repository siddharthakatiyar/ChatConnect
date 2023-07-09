package com.chatconnect.backend.models;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
@Table( name = "users", 
        uniqueConstraints = { 
          @UniqueConstraint(columnNames = "username")
        })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id;
	
	@NotBlank
	@Size(max = 50)
	private String username;
	
	@NotBlank
	@Size(max = 50)
	private String first_name;
	
	@NotBlank
	@Size(max = 50)
	private String last_name;
	
	@NotBlank
	@Size(max = 100)
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		
	}
	
	public User(String username, String first_name, String last_name, String password) {
		this.username = username;
		this.first_name = first_name;
		this.last_name = last_name;
		this.password = password;
	}

	public long getUser_id() {
		return user_id;
	}

	public String getUsername() {
		return username;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public Set<Role> getRoles() {
		return roles;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}

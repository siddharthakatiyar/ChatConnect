package com.chatconnect.backend.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.chatconnect.backend.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsGet implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long user_id;

	private String username;
	private String firstName;
	private String lastName;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsGet(Long user_id, String username, String firstName, String lastName, String password) {
		this.user_id = user_id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public static UserDetailsGet build(User user) {
		return new UserDetailsGet(user.getUser_id(), user.getUsername(), user.getFirst_name(), user.getLast_name(),
				user.getPassword());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getUser_id() {
		return user_id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public String getfirstName() {
		return firstName;
	}

	public String getlastName() {
		return lastName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		return Objects.equals(user_id, ((UserDetailsGet) o).user_id);
	}
}

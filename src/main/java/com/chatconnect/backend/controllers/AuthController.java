package com.chatconnect.backend.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatconnect.backend.models.Role;
import com.chatconnect.backend.models.User;
import com.chatconnect.backend.models.UserType;
import com.chatconnect.backend.payload.requests.LoginRequest;
import com.chatconnect.backend.payload.requests.SignupRequest;
import com.chatconnect.backend.payload.response.JwtResponse;
import com.chatconnect.backend.payload.response.MessageResponse;
import com.chatconnect.backend.repository.RoleRepository;
import com.chatconnect.backend.repository.UserRepository;
import com.chatconnect.backend.security.jwt.JwtUtils;
import com.chatconnect.backend.security.services.UserDetailsGet;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsGet userDetails = (UserDetailsGet) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUser_id(), userDetails.getUsername(), roles));
	}

	@PostMapping("/signup")
	@GetMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstName(), signUpRequest.getLastName(),
				encoder.encode(signUpRequest.getPassword()));
		// Set<String> strRoles = signUpRequest.getRole();
		// Set<Role> roles = new HashSet<>();
		// if (strRoles == null) {
		// 	Role userRole = roleRepository.findByName(UserType.ROLE_USER)
		// 			.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		// 	roles.add(userRole);
		// } else {
		// 	strRoles.forEach(role -> {
		// 		switch (role) {
		// 			case "admin":
		// 				Role adminRole = roleRepository.findByName(UserType.ROLE_ADMIN)
		// 						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		// 				roles.add(adminRole);
		// 				break;
		// 			default:
		// 				Role userRole = roleRepository.findByName(UserType.ROLE_USER)
		// 						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		// 				roles.add(userRole);
		// 				break;
		// 		}
		// 	});
		// }
		// user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}

package com.aimen.jwt.services;

import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aimen.jwt.entities.Role;
import com.aimen.jwt.entities.User;
import com.aimen.jwt.repositories.RoleRepository;
import com.aimen.jwt.repositories.UserRepository;
import com.aimen.jwt.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	public String login (String username, String password) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password)
				);
		return jwtTokenProvider.generateToken(authentication);
	}
	
	public void register (String username, String password) {
		if (userRepository.findByUsername(username).isPresent())
			throw new RuntimeException("User already exists");
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		
		Role userRole = roleRepository.findByName("ROLE_USER")
				.orElseThrow(() -> new RuntimeException("Role not found"));
		user.setRoles(Set.of(userRole));
		
		userRepository.save(user);
	}
}

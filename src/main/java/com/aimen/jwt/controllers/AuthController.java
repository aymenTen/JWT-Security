package com.aimen.jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aimen.jwt.dto.LoginRequest;
import com.aimen.jwt.dto.ResgisterRequest;
import com.aimen.jwt.services.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping ("/login")
	public ResponseEntity<String> login (
			@RequestBody LoginRequest loginRequest
			) {
		String token = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody ResgisterRequest registerRequest) {
		
		authService.register(registerRequest.getUsername(), registerRequest.getPassword());
		return ResponseEntity.ok("User registered successfully!");
	}
	
}

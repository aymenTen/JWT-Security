package com.aimen.jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@GetMapping("/adminProfile")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> adminProfile() {
		return ResponseEntity.ok("Admin profile");
	}
}

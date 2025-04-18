package com.aimen.jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@GetMapping("/userProfile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> userProfile() {
        return ResponseEntity.ok("User profile");
    }
}

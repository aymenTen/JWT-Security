package com.aimen.jwt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.aimen.jwt.entities.Role;
import com.aimen.jwt.repositories.RoleRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class JwtSecurityApplication implements CommandLineRunner {
	
	private final RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JwtSecurityApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }
	}

}

package com.aimen.jwt.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aimen.jwt.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername (String name);
}
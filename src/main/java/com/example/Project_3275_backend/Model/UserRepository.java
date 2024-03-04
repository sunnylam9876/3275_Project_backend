package com.example.Project_3275_backend.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByUsername(String topic);
	
}

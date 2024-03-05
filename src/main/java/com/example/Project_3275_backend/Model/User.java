package com.example.Project_3275_backend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

	@Column(name = "username")
	private String username;
	
	@Column(name = "password")    
    private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "status")
    private int status;
	
	public User() {
		
	}

	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.status = 0;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	// Disabled for security reason
	//needed for userSerivec-Login
	@JsonIgnore // Exclude password from serialization
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

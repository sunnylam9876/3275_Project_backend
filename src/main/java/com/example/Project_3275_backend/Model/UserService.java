package com.example.Project_3275_backend.Model;

public interface UserService {
    User registerUser(User user);
    User loginUser(String username, String password);
    User getUserById(Long userId);
    
}
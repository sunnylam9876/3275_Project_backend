package com.example.Project_3275_backend.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        // You can add additional logic such as password encoding here
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String username, String password) {
        // Your logic to authenticate user and return user object
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
    
}
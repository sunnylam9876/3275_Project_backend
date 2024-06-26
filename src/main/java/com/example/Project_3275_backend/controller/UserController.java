package com.example.Project_3275_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project_3275_backend.Model.User;
import com.example.Project_3275_backend.Model.UserRepository;
import com.example.Project_3275_backend.Model.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
//@RequestMapping("/api")

public class UserController {
	
	@Autowired
    private UserService userService;
	
	
	@Autowired
    private UserRepository userRepository;
	

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    @CrossOrigin(origins = "http://localhost:8081")
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        
        // for testing purpose
        //System.out.printf(user.getUsername() + "\n");
        //System.out.printf(user.getPassword() + "\n");
        if (loggedInUser != null) {
        	System.out.printf(loggedInUser.getUserId().toString());
            return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
            
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    

    //return the user object excluding the password.
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    	User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
        	// Create a new User object with password set to null
            User responseUser = new User();
            responseUser.setUserId(user.getUserId());
            responseUser.setUsername(user.getUsername());
            responseUser.setRole(user.getRole());
            responseUser.setStatus(user.getStatus());
            
            return new ResponseEntity<>(responseUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    @PutMapping("/{userId}/status")
    public ResponseEntity<User> updateUserStatus(@PathVariable Long userId, @RequestParam int status) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setStatus(status);
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }    
}

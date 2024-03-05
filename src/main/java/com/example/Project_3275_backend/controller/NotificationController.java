package com.example.Project_3275_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project_3275_backend.Model.Notification;
import com.example.Project_3275_backend.Model.NotificationRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class NotificationController {
	@Autowired
	NotificationRepository notificationRepository;
	
	@GetMapping("/notifications")
	public ResponseEntity<List<Notification>> getAllNotification(){
		
		try {
			
			List<Notification> notifications = new ArrayList<Notification>();

			notificationRepository.findAll().forEach(notifications::add);
			
			if(notifications.isEmpty()) {
				
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			} else {
				
				return new ResponseEntity<>(notifications, HttpStatus.OK);
			
			}
			
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}


	@GetMapping("/notifications/{userId}")
    public ResponseEntity<List<Notification>> getNotificationByUserId(@PathVariable Optional<Long> userId) {
        try {
            List<Notification> notifications = new ArrayList<>();

            if (userId.isPresent()) {
            	
            	List<Notification> notificationData = notificationRepository.findByUserId(userId.get());
            	
                if (notificationData.isEmpty()) {
                	
                	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    
                } else {
                	
                    notifications.addAll(notificationData);
                    
                }
            }

            if (notifications.isEmpty()) {
            	
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                
            } else {
            	
                return new ResponseEntity<>(notifications, HttpStatus.OK);
                
            }
            
        } catch (Exception e) {
        	
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
		
	@PostMapping("/notifications")
	public ResponseEntity<List<Notification>> createNotification(@RequestBody Notification notification){
		try {
			Notification newNotification = new Notification(notification.getUserId(), notification.getMessage(), notification.getCreatorId());
			notificationRepository.save(newNotification);
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

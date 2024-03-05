package com.example.Project_3275_backend.Model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
	List<Notification> findByUserId(long userId);
	List<Notification> findByMessage(String message);
	//List<Notification> findByCreatorId(long creatorId);
	
}

package com.example.Project_3275_backend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Project_3275_backend.Model.Article;
import com.example.Project_3275_backend.Model.ArticleRepository;
import com.example.Project_3275_backend.Model.Notification;
import com.example.Project_3275_backend.Model.NotificationRepository;
import com.example.Project_3275_backend.Model.User;
import com.example.Project_3275_backend.Model.UserRepository;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepo, ArticleRepository articleRepo, NotificationRepository notificationRepo) {
		return args -> {
			// Create users at the beginning			
			User admin1 = userRepo.save(new User("admin_1", "password_4", "admin"));
			User writer1 = userRepo.save(new User("writer_1", "password_1", "writer"));
			User writer2 = userRepo.save(new User("writer_2", "password_2", "writer"));
			User reader1 = userRepo.save(new User("reader_1", "password_3", "reader"));

			
			// Create some articles
			articleRepo.save(new Article("Title 1", "Content of article 1", writer1.getUserId()));
			articleRepo.save(new Article("Title 2", "Content of article 2", writer2.getUserId()));
			
			//Create notifications
			notificationRepo.save(new Notification(writer1.getUserId(), "Welcome!", admin1.getUserId()));
			notificationRepo.save(new Notification(writer2.getUserId(), "Welcome!", admin1.getUserId()));
			notificationRepo.save(new Notification(reader1.getUserId(), "Welcome!", admin1.getUserId()));
			notificationRepo.save(new Notification(writer1.getUserId(), "This is the Message from reader1 to writer1", reader1.getUserId()));

		};
	}
	
	

}

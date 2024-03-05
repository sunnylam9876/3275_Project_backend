package com.example.Project_3275_backend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Project_3275_backend.Model.Article;
import com.example.Project_3275_backend.Model.ArticleRepository;
import com.example.Project_3275_backend.Model.Guideline;
import com.example.Project_3275_backend.Model.GuidelineRepository;
import com.example.Project_3275_backend.Model.User;
import com.example.Project_3275_backend.Model.UserRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	ApplicationRunner init(UserRepository userRepo, ArticleRepository articleRepo, GuidelineRepository guideRepo) {
		return args -> {
			// Create users at the beginning
			User writer1 = userRepo.save(new User("writer_1", "password_1", "writer"));
			User writer2 = userRepo.save(new User("writer_2", "password_2", "writer"));
			User reader1 = userRepo.save(new User("reader_1", "password_3", "reader"));
			User admin1 = userRepo.save(new User("admin_1", "password_4", "admin"));
			
			// Create some articles
			articleRepo.save(new Article("Title 1", "Content of article 1", writer1.getUserId()));
			articleRepo.save(new Article("Title 2", "Content of article 2", writer2.getUserId()));

			// Create guidelines at the beginning
			guideRepo.save(new Guideline("Rule1"));
			guideRepo.save(new Guideline("Rule2"));
		};
	}
	
	

}

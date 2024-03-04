package com.example.Project_3275_backend;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.Project_3275_backend.Model.Article;
import com.example.Project_3275_backend.Model.ArticleRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	ApplicationRunner init(ArticleRepository articleRepo) {
		return args -> {
			articleRepo.save(new Article("Topic_1", "Content_1"));
			articleRepo.save(new Article("Topic_2", "Content_2"));
		};
	}

}

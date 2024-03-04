package com.example.Project_3275_backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project_3275_backend.Model.Article;
import com.example.Project_3275_backend.Model.ArticleRepository;

@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	
	// Get all article
	// ****for checking purpose****
	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getAllCourse(@RequestParam(required =false) String topic) {
		
		try {
			List<Article> courses = new ArrayList<Article>();
			
			if (topic == null) {
				articleRepository.findAll().forEach(courses::add);
			} else {
				articleRepository.findByTopic(topic).forEach(courses::add);
			}
			
			if(courses.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// return the contents
			return new ResponseEntity<>(courses, HttpStatus.OK);
			
		} catch (Exception e) {	//return error
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

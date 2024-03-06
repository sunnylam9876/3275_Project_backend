package com.example.Project_3275_backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project_3275_backend.Model.Article;
import com.example.Project_3275_backend.Model.ArticleRepository;
import com.example.Project_3275_backend.Model.Guideline;

@RestController
@RequestMapping("/api")
public class ArticleController {
	@Autowired
	ArticleRepository articleRepository;
	
	// Get all article
	// ****for checking purpose****
	@GetMapping("/articles")
	public ResponseEntity<List<Article>> getAllCourse(@RequestParam(required =false) String title) {
		
		try {
			List<Article> article = new ArrayList<Article>();
			
			if (title == null) {
				articleRepository.findAll().forEach(article::add);
			} else {
				articleRepository.findByTitle(title).forEach(article::add);
			}
			
			if(article.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			// return the contents
			return new ResponseEntity<>(article, HttpStatus.OK);
			
		} catch (Exception e) {	//return error
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Get article by id
	@GetMapping("/articles/{id}")
	public ResponseEntity<Article> getCourseById(@PathVariable("id") long id) {
		
		Optional<Article> course = articleRepository.findById(id);
		
		if (course.isPresent()) {
			return new ResponseEntity<>(course.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// Post method
	// Create an article
	@PostMapping("/articles")
	public ResponseEntity<Article> createArticle(@RequestBody Article article) {
		try {
			Article _article = articleRepository.save(
					new Article(article.getTitle(), article.getContent(), article.getUserId()));
			return new ResponseEntity<>(_article, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Update an article
	@PutMapping("/articles/{id}")
	public ResponseEntity<Article> updateArticle(@PathVariable("id") long id, @RequestBody Article article) {
		Optional<Article> articleData = articleRepository.findById(id);
		if (articleData.isPresent()) {
			Article _article = articleData.get();
			_article.setTitle(article.getTitle());
			_article.setContent(article.getContent());
			return new ResponseEntity<>(articleRepository.save(_article), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// Delete an article by Id
    @DeleteMapping("/articles/{id}")
    public ResponseEntity<HttpStatus> deleteArticle(@PathVariable("id") long id) {
        try {
            articleRepository.deleteById(id);
            //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

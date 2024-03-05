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

import com.example.Project_3275_backend.Model.Guideline;
import com.example.Project_3275_backend.Model.GuidelineRepository;

@RestController
@RequestMapping("/api")
public class GuidelineController {
	@Autowired
	GuidelineRepository guidelineRepository;
	
	// Get all guidelines
	@GetMapping("/guidelines")
	public ResponseEntity<List<Guideline>> getAllGuideline(@RequestParam(required=false) String content) {
		try {
			List<Guideline> guideline = new ArrayList<Guideline>();
			
			if (content == null) {
				guidelineRepository.findAll().forEach(guideline::add);
			} else {
				guidelineRepository.findByContent(content).forEach(guideline::add);
			}
			
			if(guideline.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(guideline, HttpStatus.OK);
			
		} catch (Exception e) {	
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Create a guideline
	@PostMapping("/guidelines")
	public ResponseEntity<Guideline> createArticle(@RequestBody Guideline guideline) {
		try {
			Guideline _guideline = guidelineRepository.save(
					new Guideline(guideline.getContent()));
			return new ResponseEntity<>(_guideline, HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Update a guideline
	@PutMapping("/guidelines/{id}")
	public ResponseEntity<Guideline> updateCountry(@PathVariable("id") long id, @RequestBody Guideline guideline) {
		Optional<Guideline> guidelineData = guidelineRepository.findById(id);
		if (guidelineData.isPresent()) {
			Guideline _guideline = guidelineData.get();
			_guideline.setContent(guideline.getContent());
			return new ResponseEntity<>(guidelineRepository.save(_guideline), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	// Delete a guideline by Id
	@DeleteMapping("/guidelines/{id}")
	public ResponseEntity<HttpStatus> deleteGuideline(@PathVariable("id") long id) {
		try {
			guidelineRepository.deleteById(id);
			 return new ResponseEntity<>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

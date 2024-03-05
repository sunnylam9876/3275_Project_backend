package com.example.Project_3275_backend.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project_3275_backend.Model.Comment;
import com.example.Project_3275_backend.Model.CommentRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/comments")

public class CommentController {

	@Autowired
    private CommentRepository commentRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Comment> getCommentById(@PathVariable long id) {
	    Optional<Comment> commentData = commentRepository.findById(id);
	    if (commentData.isPresent()) {
	    	return new ResponseEntity<>(commentData.get(), HttpStatus.OK);
	    } else {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	
	@GetMapping("/flagged")
	public ResponseEntity<List<Comment>> getCommentsByFlag(@RequestParam int flag) {
	    try {
	    	List<Comment> flaggedComments = new ArrayList<Comment>();
	    	commentRepository.findByFlag(flag).forEach(flaggedComments::add);
	 	    if (flaggedComments.isEmpty()) {
	 	        return new ResponseEntity<>(flaggedComments, HttpStatus.OK);
	 	    } else {
	 	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	 	    }
	    } catch (Exception e){
	    	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	   
	}
	

    @PostMapping
    public ResponseEntity<List<Comment>> createComment(@RequestBody Comment comment) {
        try{
        	Comment newComment = new Comment(comment.getUserId(), comment.getArticleId(), comment.getContent());
        	commentRepository.save(newComment);
        	return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
        	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
      
    
    //In PostMan, only need to choose raw & json. Type the content directly. no wrap and key need to be input.
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateCommentContent(@PathVariable long id, @RequestBody String content) {
        Optional<Comment> commentData = commentRepository.findById(id);
        if (commentData.isPresent()) {
            Comment _comment = commentData.get();
            _comment.setContent(content);
            _comment.setLastModifiedTime(new Date());    
            return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    //In PostMan, only need to provide the params key"flag" and value.
    @PutMapping("/{id}/flag")
    public ResponseEntity<Comment> flagComment(@PathVariable long id, @RequestParam int flag) {
        Optional<Comment> commentData = commentRepository.findById(id);
        if (commentData.isPresent()) {
            Comment _comment = commentData.get();
            _comment.setFlag(flag);
            return new ResponseEntity<>(commentRepository.save(_comment), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable long id) {
    	try {
    		commentRepository.deleteById(id);
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
}
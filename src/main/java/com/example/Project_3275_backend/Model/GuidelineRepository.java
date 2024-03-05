package com.example.Project_3275_backend.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GuidelineRepository extends JpaRepository<Guideline, Long> {
	List<Article> findByContent(String content);
}

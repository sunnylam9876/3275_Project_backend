package com.example.Project_3275_backend.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findByTopic(String topic);
	List<Article> findByContent(String content);
}

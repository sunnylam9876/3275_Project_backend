package com.example.Project_3275_backend.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
	List<Article> findByTitle(String title);
	List<Article> findByContent(String content);
}

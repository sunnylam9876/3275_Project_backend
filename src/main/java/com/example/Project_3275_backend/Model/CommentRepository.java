package com.example.Project_3275_backend.Model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUserId(long userId);
    List<Comment> findByFlag(int flag);

}

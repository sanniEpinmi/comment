package com.assignment.repository;

import java.util.List;

import com.assignment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
  //List<Comment> findByPublished(boolean published);

  List<Comment> findByTitleContaining(String title);
}

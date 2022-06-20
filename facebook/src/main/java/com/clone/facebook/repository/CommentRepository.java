package com.clone.facebook.repository;

import com.clone.facebook.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByContentsId(Long contentsId);
    Comment findByIdAndContentsId(Long id,Long contentsId);
}

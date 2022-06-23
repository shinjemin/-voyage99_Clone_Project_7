package com.clone.facebook.repository;

import com.clone.facebook.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByOrderByModifiedAtDesc();
}

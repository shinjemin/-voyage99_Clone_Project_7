package com.clone.facebook.repository;

import com.clone.facebook.models.CommentLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentLikesRepository extends JpaRepository<CommentLikes,Long> {
    List<CommentLikes> findAllByCommentIdAndLike(Long commentId,int like);
    CommentLikes findByCommentId(Long commentId);
}

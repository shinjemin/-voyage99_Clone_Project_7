package com.clone.facebook.service;

import com.clone.facebook.dto.CommentDto;
import com.clone.facebook.models.Comment;
import com.clone.facebook.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public List<Comment> getComment(Long contentsId){
        return commentRepository.findAllByContentsId(contentsId);
    }

    @Transactional
    public Long postComment(Long contentsId, CommentDto commentDto,Long userId){
        Comment comment = new Comment(contentsId,commentDto,userId);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Long updateComment(Long contentsId,Long id,CommentDto commentDto,Long userId){
        Comment comment = commentRepository.findByIdAndContentsId(id,contentsId);
        comment.update(contentsId,commentDto,userId);
        return comment.getId();
    }

    @Transactional
    public Long deleteComment(Long contentsId,Long id){
        Comment comment = commentRepository.findByIdAndContentsId(id,contentsId);
        commentRepository.delete(comment);
        return comment.getId();
    }
}

package com.clone.facebook.service;

import com.clone.facebook.dto.CommentLikesDto;
import com.clone.facebook.models.CommentLikes;
import com.clone.facebook.repository.CommentLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;


    @Transactional
    public Long getLikes(Long commentId){
        int like = 1;
        return (long)commentLikesRepository.findAllByCommentIdAndLike(commentId,like).size();
    }

    @Transactional
    public Long changeLike(Long commentId, CommentLikesDto commentLikesDto,Long userId){
        if(commentLikesRepository.findByCommentId(commentId) ==null){
            CommentLikes commentLikes = new CommentLikes(commentId,userId,commentLikesDto);
            commentLikesRepository.save(commentLikes);
            return commentLikes.getCommentId();
        }else{
            CommentLikes commentLikes = commentLikesRepository.findByCommentId(commentId);
            commentLikes.changeLike(commentLikesDto.getLike());
            return commentLikes.getCommentId();

        }

    }
}

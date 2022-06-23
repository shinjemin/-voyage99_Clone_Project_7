package com.clone.facebook.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.clone.facebook.dto.CommentLikesRequestDto;

import com.clone.facebook.models.CommentLikes;
import com.clone.facebook.models.User;
import com.clone.facebook.repository.CommentLikesRepository;
import com.clone.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.auth0.jwt.JWT.decode;

@RequiredArgsConstructor
@Service
public class CommentLikesService {

    private final CommentLikesRepository commentLikesRepository;
    private final UserRepository userRepository;

    private User getUser(String authorization){
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));
        return user;

    }
    @Transactional
    public Long getLikes(Long commentId){
        int like = 1;
        return (long)commentLikesRepository.findAllByCommentIdAndLike(commentId,like).size();
    }

    @Transactional
    public Long changeLike(Long commentId, CommentLikesRequestDto commentLikesDto, String authorization){



        if(commentLikesRepository.findByCommentIdAndUserId(commentId, getUser(authorization).getId()) ==null){
            CommentLikes commentLikes = new CommentLikes(commentId,getUser(authorization).getId(),commentLikesDto);
            commentLikesRepository.save(commentLikes);

            return commentLikes.getCommentId();
        }else{
            CommentLikes commentLikes = commentLikesRepository.findByCommentIdAndUserId(commentId,getUser(authorization).getId());
            commentLikes.changeLike(commentLikesDto.getLike());
            return commentLikes.getCommentId();

        }

    }
}

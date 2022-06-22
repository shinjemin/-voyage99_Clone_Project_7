package com.clone.facebook.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.clone.facebook.dto.CommentDto;
import com.clone.facebook.models.Comment;
import com.clone.facebook.models.User;
import com.clone.facebook.repository.CommentRepository;
import com.clone.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static com.auth0.jwt.JWT.decode;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private Long getId(String authorization){
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));
    return user.getId();

    }

    @Transactional
    public List<Comment> getComment(Long contentsId){
        return commentRepository.findAllByContentsId(contentsId);
    }

    @Transactional
    public Long postComment(Long contentsId, CommentDto commentDto,String authorization){

        Comment comment = new Comment(contentsId,commentDto,getId(authorization));
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public Long updateComment(Long contentsId,Long id,CommentDto commentDto,String authorization){
        if(commentRepository.findByIdAndContentsId(id,contentsId).getUserId().equals(getId(authorization))){
            Comment comment = commentRepository.findByIdAndContentsId(id,contentsId);
            comment.update(contentsId,commentDto,getId(authorization));
            return comment.getId();
        }else {
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다");
        }

    }

    @Transactional
    public Long deleteComment(Long contentsId,Long id, String authorization){
        if(commentRepository.findByIdAndContentsId(id,contentsId).getUserId().equals(getId(authorization))){
            Comment comment = commentRepository.findByIdAndContentsId(id,contentsId);
            commentRepository.delete(comment);
            return comment.getId();
        }else {
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다");
        }
    }
}

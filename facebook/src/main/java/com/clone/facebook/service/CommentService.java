package com.clone.facebook.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.clone.facebook.dto.CommentRequestDto;
import com.clone.facebook.dto.CommentResponseDto;


import com.clone.facebook.models.Comment;

import com.clone.facebook.models.User;
import com.clone.facebook.repository.CommentRepository;
import com.clone.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

import static com.auth0.jwt.JWT.decode;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private User getUser(String authorization){
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));
    return user;

    }

    @Transactional
    public List<CommentResponseDto> getComment(Long contentsId, String authorization){


        List<Comment> comments = commentRepository.findAllByContentsId(contentsId);
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        for(Comment comment : comments) {

            CommentResponseDto commentResponseDto = new CommentResponseDto(comment,getUser(authorization));
            commentResponseDtoList.add(commentResponseDto);
        }

        return commentResponseDtoList;
    }

    @Transactional
    public Long postComment(Long contentsId, CommentRequestDto commentDto, String authorization){

        Comment comment = new Comment(contentsId,commentDto,getUser(authorization));
        commentRepository.save(comment);

        return comment.getId();
    }

    @Transactional
    public Long updateComment(Long contentsId, Long id, CommentRequestDto commentDto, String authorization){
        if(commentRepository.findByIdAndContentsId(id,contentsId).getUserId().equals(getUser(authorization).getId())){
            Comment comment = commentRepository.findByIdAndContentsId(id,contentsId);
            comment.update(contentsId,commentDto,getUser(authorization));

            return comment.getId();
        }else {
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다");
        }

    }

    @Transactional
    public Long deleteComment(Long contentsId,Long id, String authorization){
        if(commentRepository.findByIdAndContentsId(id,contentsId).getUserId().equals(getUser(authorization).getId())){
            Comment comment = commentRepository.findByIdAndContentsId(id,contentsId);
            commentRepository.delete(comment);
            return comment.getId();
        }else {
            throw new IllegalArgumentException("회원정보가 일치하지 않습니다");
        }
    }
}

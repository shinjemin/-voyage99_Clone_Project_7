package com.clone.facebook.service;


import com.auth0.jwt.interfaces.DecodedJWT;

import com.clone.facebook.dto.CommentLikesDto;

import com.clone.facebook.models.ContentsLikes;

import com.clone.facebook.models.User;
import com.clone.facebook.repository.ContentsLikesRepository;
import com.clone.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.auth0.jwt.JWT.decode;


@RequiredArgsConstructor
@Service
public class ContentsLikesService {

    private final ContentsLikesRepository contentsLikesRepository;
    private final UserRepository userRepository;


    @Transactional
    public Long getLikes(Long contentsId){
        int like = 1;
        return (long)contentsLikesRepository.findAllByContentsIdAndLike(contentsId,like).size();
    }

    @Transactional
    public Long changeLike(Long contentsId, CommentLikesDto commentLikesDto, String authorization){
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));


        if(contentsLikesRepository.findByContentsIdAndUserId(contentsId, user.getId()) ==null){
            ContentsLikes contentsLikes = new ContentsLikes(contentsId,user.getId(),commentLikesDto);
            contentsLikesRepository.save(contentsLikes);
            return contentsLikes.getContentsId();
        }else{
            ContentsLikes contentsLikes = contentsLikesRepository.findByContentsIdAndUserId(contentsId, user.getId());
            contentsLikes.changeLike(commentLikesDto.getLike());
            return contentsLikes.getContentsId();

        }

    }
}

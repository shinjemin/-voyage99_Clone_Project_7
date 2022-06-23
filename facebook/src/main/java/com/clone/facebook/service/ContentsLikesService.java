package com.clone.facebook.service;


import com.auth0.jwt.interfaces.DecodedJWT;

import com.clone.facebook.dto.CommentLikesRequestDto;


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

    private User getUser(String authorization){
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));
        return user;

    }


    @Transactional
    public Long getLikes(Long contentsId){
        int like = 1;
        return (long)contentsLikesRepository.findAllByContentsIdAndLike(contentsId,like).size();
    }

    @Transactional
    public Long changeLike(Long contentsId, CommentLikesRequestDto commentLikesDto, String authorization){
        if(contentsLikesRepository.findByContentsIdAndUserId(contentsId, getUser(authorization).getId()) ==null){
            ContentsLikes contentsLikes = new ContentsLikes(contentsId,getUser(authorization).getId(),commentLikesDto);
            contentsLikesRepository.save(contentsLikes);

            return contentsLikes.getContentsId();
        }else{
            ContentsLikes contentsLikes = contentsLikesRepository.findByContentsIdAndUserId(contentsId, getUser(authorization).getId());
            contentsLikes.changeLike(commentLikesDto.getLike());

            return contentsLikes.getContentsId();

        }

    }
}

package com.clone.facebook.service;

import com.clone.facebook.dto.CommentLikesDto;

import com.clone.facebook.models.ContentsLikes;

import com.clone.facebook.repository.ContentsLikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@RequiredArgsConstructor
@Service
public class ContentsLikesService {

    private final ContentsLikesRepository contentsLikesRepository;


    @Transactional
    public Long getLikes(Long commentId){
        int like = 1;
        return (long)contentsLikesRepository.findAllByContentsIdAndLike(commentId,like).size();
    }

    @Transactional
    public Long changeLike(Long contentsId, CommentLikesDto commentLikesDto, Long userId){
        if(contentsLikesRepository.findByContentsId(contentsId) ==null){
            ContentsLikes contentsLikes = new ContentsLikes(contentsId,userId,commentLikesDto);
            contentsLikesRepository.save(contentsLikes);
            return contentsLikes.getContentsId();
        }else{
            ContentsLikes contentsLikes = contentsLikesRepository.findByContentsId(contentsId);
            contentsLikes.changeLike(commentLikesDto.getLike());
            return contentsLikes.getContentsId();

        }

    }
}

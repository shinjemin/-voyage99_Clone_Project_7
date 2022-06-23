package com.clone.facebook.controller;

import com.clone.facebook.dto.CommentLikesRequestDto;


import com.clone.facebook.service.ContentsLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class ContentsLikesController {

    private final ContentsLikesService contentsLikesService;

    @GetMapping("api/contents/like/{contentsId}")
    public Long getLike(@PathVariable Long contentsId){
        return contentsLikesService.getLikes(contentsId);
    }

    @PostMapping("api/contents/like/{contentsId}")
    public Long changeLike(@PathVariable Long contentsId, @RequestBody CommentLikesRequestDto commentLikesDto, @RequestHeader("Authorization") String authorization){
        return contentsLikesService.changeLike(contentsId,commentLikesDto,authorization);

    }
}
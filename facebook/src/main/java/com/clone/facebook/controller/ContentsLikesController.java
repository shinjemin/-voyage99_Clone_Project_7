package com.clone.facebook.controller;

import com.clone.facebook.dto.CommentLikesDto;

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
    public Long changeLike(@PathVariable Long contentsId,  @RequestBody CommentLikesDto commentLikesDto){
        Long userId = (long)0;
        return contentsLikesService.changeLike(contentsId,commentLikesDto,userId);

    }
}
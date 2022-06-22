package com.clone.facebook.controller;

import com.clone.facebook.dto.CommentLikesDto;
import com.clone.facebook.service.CommentLikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentLikesController {

    private final CommentLikesService commentLikesService;

    @GetMapping("api/comment/like/{commentId}")
    public Long getLike(@PathVariable Long commentId){
        return commentLikesService.getLikes(commentId);
    }

    @PostMapping("api/comment/like/{commentId}")
    public Long changeLike(@PathVariable Long commentId,@RequestBody CommentLikesDto commentLikesDto, @RequestHeader("Authorization") String authorization){

        return commentLikesService.changeLike(commentId,commentLikesDto,authorization);

    }
}

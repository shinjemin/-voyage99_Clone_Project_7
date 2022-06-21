package com.clone.facebook.controller;

import com.clone.facebook.dto.CommentDto;
import com.clone.facebook.models.Comment;
import com.clone.facebook.service.CommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/api/board/get/{contentsId}")
    public List<Comment> getComment(@PathVariable Long contentsId){


        return commentService.getComment(contentsId);
    }

    @PostMapping("/api/board/post/{contentsId}")
    public Long postComment(@PathVariable Long contentsId, @RequestBody CommentDto commentDto){
        Long userId = (long)0;
        return commentService.postComment(contentsId,commentDto,userId);
    }

    @PutMapping("/api/board/put/{contentsId}/{id}")
    public Long updateComment(@PathVariable Long contentsId,@PathVariable Long id, @RequestBody CommentDto commentDto ){
        Long userId = (long)0;
        return commentService.updateComment(contentsId,id,commentDto,userId);
    }

    @DeleteMapping("/api/board/delete/{contentsId}/{id}")
    public Long deleteComment(@PathVariable Long contentsId,@PathVariable Long id ){
        return commentService.deleteComment(contentsId,id);
    }
}

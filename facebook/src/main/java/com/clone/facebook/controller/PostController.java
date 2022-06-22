package com.clone.facebook.controller;

import com.clone.facebook.dto.PostRequestDto;
import com.clone.facebook.dto.PostResponseDto;
import com.clone.facebook.models.TokenDecode;
import com.clone.facebook.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("/api/board")
    public Long postPosts(@RequestBody PostRequestDto postRequestDto, @RequestHeader("Authorization") String authorization){

        return postService.postPost(postRequestDto, authorization);
    }

    @GetMapping("/api/board")
    public List<PostResponseDto> getPosts(@RequestHeader("Authorization") String authorization){
        return postService.getPosts(authorization);
    }

    @GetMapping("/api/board/user/{postId}")
    public PostResponseDto getPostDetail(@PathVariable Long postId, @RequestHeader("Authorization") String authorization){
        return postService.getPostDetail(postId, authorization);
    }

    @PutMapping("/api/board/{postId}")
    public Long updatePost(@PathVariable Long postId,
                           @RequestBody PostRequestDto postRequestDto,
                           @RequestHeader("Authorization") String authorization){
        postService.updatePost(postId, postRequestDto, authorization);
        return postId;
    }

    @DeleteMapping("/api/board/{postId}")
    public Long deletePost(@PathVariable Long postId, @RequestHeader ("Authorization") String authorization){
        return postService.deletePost(postId, authorization);
    }
}

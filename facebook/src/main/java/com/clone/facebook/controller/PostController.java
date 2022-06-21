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
    public void postPosts(@RequestBody PostRequestDto postRequestDto, HttpServletRequest httpRequest){
        TokenDecode tokenDecode = (TokenDecode) httpRequest.getAttribute("decode");
        postService.postPost(postRequestDto, tokenDecode);
    }

    @GetMapping("/api/board")
    public List<PostResponseDto> getPosts(){
        return postService.getPosts();
    }

    @GetMapping("/api/board/user/{postId}")
    public PostResponseDto getPostDetail(@PathVariable Long postId){
        return postService.getPostDetail(postId);
    }

    @PutMapping("/api/board/{postId}")
    public Long updatePost(@PathVariable Long postId,
                           @RequestBody PostRequestDto postRequestDto,
                           HttpServletRequest httpServletRequest){
        TokenDecode tokenDecode = (TokenDecode) httpServletRequest.getAttribute("decode");
        postService.updatePost(postId, postRequestDto, tokenDecode);
        return postId;
    }

    @DeleteMapping("/api/board/{postId}")
    public Long deletePost(@PathVariable Long postId, HttpServletRequest httpServletRequest){
        TokenDecode tokenDecode = (TokenDecode) httpServletRequest.getAttribute("decode");
        return postService.deletePost(postId, tokenDecode);
    }
}

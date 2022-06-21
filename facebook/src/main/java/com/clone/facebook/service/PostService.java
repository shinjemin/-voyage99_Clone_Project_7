package com.clone.facebook.service;

import com.clone.facebook.dto.PostRequestDto;
import com.clone.facebook.dto.PostResponseDto;
import com.clone.facebook.models.Post;
import com.clone.facebook.models.TokenDecode;
import com.clone.facebook.models.User;
import com.clone.facebook.repository.PostRepository;
import com.clone.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public void postPost(PostRequestDto postRequestDto, TokenDecode tokenDecode) {
        User user = userRepository.findById(tokenDecode.getId()).orElseThrow(
                () -> new NullPointerException("ID DOES NOT EXIST")
        );

        Post post = new Post(postRequestDto, user.getFamilyName(), user.getFamilyName());
        postRepository.save(post);
    }


    public List<PostResponseDto> getPosts() {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for(Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);
        }
        return postResponseDtoList;
    }

    public PostResponseDto getPostDetail(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("ID DOES NOT EXIST")
        );
        PostResponseDto postResponseDto = new PostResponseDto(post);
        return postResponseDto;
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto, TokenDecode tokenDecode) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("ID DOES NOT EXIST")
        );
        User user = userRepository.findById(tokenDecode.getId()).orElseThrow(
                () -> new NullPointerException("ID DOES NOT EXIST")
        );
        if(!post.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("ONLY THE ORIGINAL POSTER HAVE THE RIGHT TO EDIT");
        }

        post.update(postRequestDto, user.getId());


    }

    @Transactional
    public Long deletePost(Long postId, TokenDecode tokenDecode) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("NO POST TO BE DELETED")
        );
        User user = userRepository.findById(tokenDecode.getId()).orElseThrow(
                () -> new NullPointerException("ID DOES NOT EXIST")
        );
        if(!post.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("ONLY THE ORIGINAL POSTER HAS THE RIGHT TO DELETE");
        }
        postRepository.deleteById(post.getId());
        return postId;
    }
}

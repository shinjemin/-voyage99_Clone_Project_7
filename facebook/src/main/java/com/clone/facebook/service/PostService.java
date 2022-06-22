package com.clone.facebook.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
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


    public Long postPost(PostRequestDto postRequestDto, String authorization) {
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token); //
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));

        Post post = new Post(postRequestDto, user);

        return postRepository.save(post).getId();
    }

    @Transactional
    public Long deletePost(Long postId, String authorization) {
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("NO POST TO BE DELETED")
        );
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST")
        );
        if(!post.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("ONLY THE ORIGINAL POSTER HAS THE RIGHT TO DELETE");
        }
        postRepository.deleteById(post.getId());
        return postId;
    }

    @Transactional
    public void updatePost(Long postId, PostRequestDto postRequestDto, String authorization) {
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new NullPointerException("ID DOES NOT EXIST")
        );
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));
        if(!post.getUser().getId().equals(user.getId())){
            throw new IllegalArgumentException("ONLY THE ORIGINAL POSTER HAVE THE RIGHT TO EDIT");
        }
        post.update(postRequestDto, user.getId());
    }

    public List<PostResponseDto> getPosts(String authorization) {
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);

        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();

        if(decodeToken != null) {
            for (Post post : posts) {
                PostResponseDto postResponseDto = new PostResponseDto(post);
                postResponseDtoList.add(postResponseDto);
            }
        }
        return postResponseDtoList;
    }

    public PostResponseDto getPostDetail(Long postId, String authorization) {
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);

        PostResponseDto postResponseDto = null;

        if(decodeToken != null){
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new NullPointerException("PostId DOES NOT EXIST")
            );
            postResponseDto = new PostResponseDto(post);
        }
        return postResponseDto;
    }

    public DecodedJWT decode(String token){
        Algorithm algorithm = Algorithm.HMAC256("rlaalswnrkgoTdma"); //use more secure key
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer("gkdgo99") // 발급자
                .build(); //Reusable verifier instance

        DecodedJWT jwt = verifier.verify(token);
        return jwt;
    }
}

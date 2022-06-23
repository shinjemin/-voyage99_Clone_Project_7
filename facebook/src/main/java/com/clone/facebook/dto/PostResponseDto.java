package com.clone.facebook.dto;

import com.clone.facebook.models.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private String familyName;
    private String imageURL;
    private String content;
    private Long likes;
    private String givenName;

    private String mail;

    private LocalDateTime createdAt;
    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.familyName = post.getFamilyName();
        this.imageURL = post.getImageUrl();
        this.content = post.getContent();
        this.likes = post.getLikes();
        this.givenName = post.getGivenName();
        this.createdAt = post.getCreatedAt();
        this.mail = post.getMail();
    }
}

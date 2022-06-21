package com.clone.facebook.dto;

import com.clone.facebook.models.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseDto {
    private Long postId;
    private String familyName;
    private String imageURL;
    private String content;
    private Long likes;

    public PostResponseDto(Post post){
        this.postId = post.getId();
        this.familyName = post.getFamilyName();
        this.imageURL = post.getImageURL();
        this.content = post.getContent();
        this.likes = post.getLikes();
    }
}


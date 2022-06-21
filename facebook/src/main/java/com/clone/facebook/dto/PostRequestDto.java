package com.clone.facebook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {
    private String content;
    private String imageUrl;
    private Long userId;


    public PostRequestDto(String content, String imageUrl){
        this.content = content;
        this.imageUrl = imageUrl;
    }
}

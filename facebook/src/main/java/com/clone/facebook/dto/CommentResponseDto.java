package com.clone.facebook.dto;


import com.clone.facebook.models.Comment;
import com.clone.facebook.models.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {



    private Long id;
    private Long userId;
    private Long contentsId;
    private String comment;
    private String familyName;
    private String givenName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;



    public CommentResponseDto(Comment comment, User user) {
        this.id = comment.getId();
        this.userId = comment.getUserId();
        this.contentsId = comment.getContentsId();
        this.comment = comment.getComment();
        this.familyName = user.getFamilyName();
        this.givenName = user.getGivenName();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }


}

package com.clone.facebook.models;


import com.clone.facebook.dto.CommentLikesRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class CommentLikes {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long commentId;
    @Column(nullable = false)
    private int like;

    @Column(nullable = false)
    private Long userId;


    public CommentLikes(Long commentId, Long userId, CommentLikesRequestDto commentLikesDto){
        this.commentId=commentId;
        this.like = commentLikesDto.getLike();
        this.userId = userId;
    }

    public void changeLike(int like){
        this.like = like;
    }
}

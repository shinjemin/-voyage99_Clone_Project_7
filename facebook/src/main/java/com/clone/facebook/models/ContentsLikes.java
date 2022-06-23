package com.clone.facebook.models;

import com.clone.facebook.dto.CommentLikesRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;




@NoArgsConstructor
@Getter
@Entity
public class ContentsLikes {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private Long contentsId;
    @Column(nullable = false)
    private int like;

    @Column(nullable = false)
    private Long userId;


    public ContentsLikes(Long commentId, Long userId, CommentLikesRequestDto commentLikesDto){
        this.contentsId=commentId;
        this.like = commentLikesDto.getLike();
        this.userId = userId;
    }

    public void changeLike(int like){
        this.like = like;
    }
}
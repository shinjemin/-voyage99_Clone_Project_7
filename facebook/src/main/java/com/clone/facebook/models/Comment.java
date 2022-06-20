package com.clone.facebook.models;

import com.clone.facebook.dto.CommentDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long contentsId;

    @Column(nullable = false)
    private String comment;

    public Comment(Long contentsId,CommentDto commentDto,Long userId){
        this.userId = userId;
        this.comment = commentDto.getComment();
        this.contentsId = contentsId;
    }
    public void  update(Long contentsId,CommentDto commentDto,Long userId){
        this.userId = userId;
        this.comment = commentDto.getComment();
        this.contentsId = contentsId;
    }

}

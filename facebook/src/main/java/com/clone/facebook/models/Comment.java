package com.clone.facebook.models;

import com.clone.facebook.dto.CommentRequestDto;
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

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String givenName;

    public Comment(Long contentsId, CommentRequestDto commentDto, User user){
        this.userId = user.getId();
        this.comment = commentDto.getComment();
        this.contentsId = contentsId;
        this.familyName = user.getFamilyName();
        this.givenName = user.getGivenName();
    }
    public void  update(Long contentsId, CommentRequestDto commentDto, User user){
        this.userId = user.getId();
        this.comment = commentDto.getComment();
        this.contentsId = contentsId;
        this.familyName = user.getFamilyName();
        this.givenName = user.getGivenName();
    }

}

package com.clone.facebook.models;

import com.clone.facebook.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Post extends Timestamped{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = true)
    private String imageURL;

    @Column(nullable = true)
    private Long likes;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private String familyName;

    @Column(nullable = false)
    private String givenName;

    public Post(PostRequestDto postRequestDto, User user){
        this.content = postRequestDto.getContent();
        this.user = user;
        this.familyName = user.getFamilyName();
        this.givenName = user.getGivenName();
    }

    public void update(PostRequestDto postRequestDto, Long id) {
        this.content = postRequestDto.getContent();
        this.imageURL = postRequestDto.getImageUrl();
    }
}
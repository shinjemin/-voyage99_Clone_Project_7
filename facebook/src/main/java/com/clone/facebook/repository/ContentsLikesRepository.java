package com.clone.facebook.repository;


import com.clone.facebook.models.ContentsLikes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentsLikesRepository extends JpaRepository<ContentsLikes,Long> {
    List<ContentsLikes> findAllByContentsIdAndLike(Long contentsId,int like);
    ContentsLikes findByContentsId(Long contentsId);
}

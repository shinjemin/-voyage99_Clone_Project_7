//package com.clone.facebook.models;
//
//import com.clone.facebook.dto.ImageDto;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Getter
//@NoArgsConstructor
//@Entity
//@Table(name = "tbl_user_image")
//public class UserImage {
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @Id
//    private Long userImageId;
//
//    @Column(nullable = false)
//    private String imageUrl;
//
//    @Column(nullable = false)
//    private String fileName;
//
//    public UserImage(ImageDto imageDto){
//        this.imageUrl = imageDto.getImageUrl();
//        this.fileName = imageDto.getFileName();
//    }
//}
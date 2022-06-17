package com.clone.facebook.dto;

import lombok.Getter;

@Getter
public class UserDto {
    private String familyName;
    private String givenName;
    private String mail;
    private String password;
    private int year;
    private int month;
    private int day;
    private String imageUrl;
}

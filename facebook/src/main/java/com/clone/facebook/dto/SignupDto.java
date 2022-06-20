package com.clone.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter

public class SignupDto {
    private String familyName;
    private String givenName;
    private String mail;
    private String password;
    private String month;
    private String day;
    private String year;

}

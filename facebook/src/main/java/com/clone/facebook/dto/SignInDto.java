package com.clone.facebook.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class SignInDto {
    private String mail;
    private String password;
}

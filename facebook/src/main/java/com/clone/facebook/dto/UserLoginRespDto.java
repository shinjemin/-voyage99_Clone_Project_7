package com.clone.facebook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserLoginRespDto {
    boolean result;
    String errorMsg;
    String token;
    String refreshToken;

    String familyName;
    String givenName;
    String mail;

    public UserLoginRespDto(boolean result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;
    }

    public UserLoginRespDto(boolean result, String token, String refreshToken, String errorMsg, String familyName, String givenName, String mail) {
        this.result = result;
        this.errorMsg = errorMsg;
        this.token = token;
        this.refreshToken = refreshToken;
        this.familyName = familyName;
        this.givenName = givenName;
        this.mail = mail;
    }
}


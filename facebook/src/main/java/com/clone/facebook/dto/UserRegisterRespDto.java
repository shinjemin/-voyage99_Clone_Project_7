package com.clone.facebook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserRegisterRespDto {
    boolean result;
    String errorMsg;


    public UserRegisterRespDto(boolean result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;

    }

}

package com.clone.facebook.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserTokenRespDto {
    boolean result;
    String errorMsg;

    String payload;

    public UserTokenRespDto(boolean result, String errorMsg) {
        this.result = result;
        this.errorMsg = errorMsg;
    }

    public UserTokenRespDto(boolean result, String errorMsg, String payload) {
        this.result = result;
        this.errorMsg = errorMsg;
        this.payload = payload;
    }
}

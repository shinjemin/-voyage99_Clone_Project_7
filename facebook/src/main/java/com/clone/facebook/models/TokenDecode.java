package com.clone.facebook.models;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TokenDecode {
    String token;
    String mail;
    Long id;

    public TokenDecode(DecodedJWT jwt) {
        this.token = jwt.getToken();
        this.id = Long.parseLong(jwt.getClaim("id").toString());
        this.mail = jwt.getClaim("mail").toString();

    }

}

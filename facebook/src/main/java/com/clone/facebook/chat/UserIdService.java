package com.clone.facebook.chat;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.clone.facebook.models.User;
import com.clone.facebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.auth0.jwt.JWT.decode;

@RequiredArgsConstructor
@Service
public class UserIdService {
    private final UserRepository userRepository;

    public User getUser(String authorization){
        String token = authorization.substring(7);
        DecodedJWT decodeToken = decode(token);
        User user = userRepository.findById(Long.parseLong(decodeToken.getClaim("id").toString()))
                .orElseThrow(() -> new NullPointerException("ID DOES NOT EXIST"));
        return user;
    }
}

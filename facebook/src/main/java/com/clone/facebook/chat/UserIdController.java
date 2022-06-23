package com.clone.facebook.chat;

import com.clone.facebook.models.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserIdController {
    private final UserIdService userIdService;

    @GetMapping("api/token")
    public User gettoken(@RequestHeader("Authorization") String authorization){
        return userIdService.getUser(authorization);
    }

}

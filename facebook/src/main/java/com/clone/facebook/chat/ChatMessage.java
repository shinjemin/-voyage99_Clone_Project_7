package com.clone.facebook.chat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK
    }
    private String familyName;
    private String givenName;
    private Long userId;
    private MessageType type;
    private String roomId;

    private String message;
}


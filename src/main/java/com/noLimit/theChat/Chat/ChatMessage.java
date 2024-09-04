package com.noLimit.theChat.Chat;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder   //chatMessage.builder().content("hello").sender("noLimit").type(MessageType)
public class ChatMessage {
    private String content;
    private String sender; //hastkhdemha lama el user yetla3 mn el chat
    private MessageType type; //MessageType.LEAVER
}

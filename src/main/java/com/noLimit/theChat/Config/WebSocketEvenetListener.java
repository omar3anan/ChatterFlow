package com.noLimit.theChat.Config;

import com.noLimit.theChat.Chat.ChatMessage;
import com.noLimit.theChat.Chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j //for logging => log.info
public class WebSocketEvenetListener {

    private final SimpMessageSendingOperations messageTemplate;
    @EventListener //Listing on SessionDisconnectEvent => awel ma had yetla3 y log 3la tol
    public void handleWebSocketDisconnectListener(
            SessionDisconnectEvent event
    ) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("usernamex");
        if (username != "null") {
            log.info("User disconnect:{}", username);
            var theChatMessage = ChatMessage
                    .builder()  //@Builder el mawgoda gowa el chatMessage class
                    .type(MessageType.LEAVER) //setters
                    .sender(username) //setters
                    .build();

            messageTemplate.convertAndSend("/topic/public",theChatMessage);
        }
    }
}

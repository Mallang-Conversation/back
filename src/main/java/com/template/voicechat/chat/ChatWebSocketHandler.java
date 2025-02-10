package com.template.voicechat.chat;

import com.template.voicechat.game.GameType;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChatWebSocketHandler extends TextWebSocketHandler {
    private final ChatService chatService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        log.info("새로운 WebSocket 연결 수립: {}", session.getId());
        session.sendMessage(new TextMessage("SESSION_ID:" + session.getId()));
        chatService.startChat(session.getId(), getGameType(session));
    }

    private GameType getGameType(WebSocketSession session) throws IOException {
        String query = session.getUri().getQuery();
        if (query == null) {
            session.close();
            throw new IllegalArgumentException("게임 타입이 지정되지 않았습니다.");
        }
        return GameType.valueOf(extractQueryParam(query, "game"));
    }

    private String extractQueryParam(String query, String key) {
        if (query == null) return null;
        for (String param : query.split("&")) {
            String[] pair = param.split("=");
            if (pair.length == 2 && pair[0].equals(key)) {
                return pair[1];
            }
        }
        return null;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
        throws IOException {
        log.info("WebSocket 메시지 수신: {} (메시지: {})", session.getId(), message.getPayload());
        String response = chatService.sendMessage(session.getId(), message.getPayload());
        session.sendMessage(new TextMessage(response));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        log.info("WebSocket 연결 종료: {} (상태: {})", session.getId(), status);
        chatService.endChat(session.getId());
    }
}
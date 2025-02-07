package com.template.voicechat.chat;

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
    private final ChatSessionManager sessionManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        log.info("새로운 WebSocket 연결 수립: {}", session.getId());
        sessionManager.add(session.getId());
        session.sendMessage(new TextMessage("SESSION_ID:" + session.getId()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
        throws IOException {
        log.info("WebSocket 메시지 수신: {} (메시지: {})", session.getId(), message.getPayload());
        session.sendMessage(new TextMessage("RECEIVED:" + message.getPayload()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        log.info("WebSocket 연결 종료: {} (상태: {})", session.getId(), status);
        sessionManager.remove(session.getId());
    }
}
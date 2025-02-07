package com.template.voicechat.chat;

import com.template.voicechat.text.*;
import java.io.IOException;
import java.util.*;
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
    private final TextGenerationService textGenerationService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        log.info("새로운 WebSocket 연결 수립: {}", session.getId());
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage("assistant", "You are a helpful assistant"));
        sessionManager.add(session.getId(), messages);
        session.sendMessage(new TextMessage("SESSION_ID:" + session.getId()));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
        throws IOException {
        log.info("WebSocket 메시지 수신: {} (메시지: {})", session.getId(), message.getPayload());
        List<ChatMessage> messages = sessionManager.getMessages(session.getId());
        messages.add(new ChatMessage("user", message.getPayload()));
        String response = textGenerationService.generate(messages);
        session.sendMessage(new TextMessage(response));
        messages.add(new ChatMessage("assistant", response));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        log.info("WebSocket 연결 종료: {} (상태: {})", session.getId(), status);
        sessionManager.remove(session.getId());
    }
}
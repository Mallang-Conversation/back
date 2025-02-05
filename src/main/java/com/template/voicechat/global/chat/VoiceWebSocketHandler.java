package com.template.voicechat.global.chat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

@Component
@Slf4j
public class VoiceWebSocketHandler extends BinaryWebSocketHandler {


    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        log.info("새로운 WebSocket 연결 수립: {}", session.getId());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message){
        byte[] audioData = message.getPayload().array();
        log.info("세션 {}: 수신한 음성 데이터 ({} 바이트)", session.getId(), audioData.length);

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        log.info("WebSocket 연결 종료: {} (상태: {})", session.getId(), status);
    }
}
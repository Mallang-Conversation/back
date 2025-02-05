package com.template.voicechat.global.config;

import com.template.voicechat.global.chat.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final VoiceWebSocketHandler voiceWebSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(voiceWebSocketHandler, "/ws/voice")
            .setAllowedOrigins("*"); // 실제 서비스에서는 도메인을 제한하는 것이 좋습니다.
    }
}
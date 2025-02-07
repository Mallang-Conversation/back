package com.template.voicechat.audio;

import com.template.voicechat.chat.ChatSessionManager;
import com.template.voicechat.stt.SttService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AudioService {
    private final SttService sttService;
    private final ChatSessionManager sessionManager;

    public String convertVoiceToText(MultipartFile file) {
        validateSession(file.getOriginalFilename());
        return sttService.convertVoiceToText(file);
    }

    private void validateSession(String originalFilename) {
        String sessionId = originalFilename.replace(".wav", "");
        if (!sessionManager.contains(sessionId)) {
            throw new IllegalArgumentException("Invalid session ID: " + sessionId);
        }
    }
}
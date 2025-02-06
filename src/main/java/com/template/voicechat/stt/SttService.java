package com.template.voicechat.stt;

import org.springframework.web.multipart.MultipartFile;

public interface SttService {
    String convertVoiceToText(MultipartFile wavFile);
}
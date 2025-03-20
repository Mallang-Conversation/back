package com.mallang.stt;

import org.springframework.web.multipart.MultipartFile;

public interface SttService {
    String convertVoiceToText(MultipartFile wavFile);
}
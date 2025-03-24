package com.mallang.audio;

import com.mallang.stt.SttService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AudioService {
    private final SttService sttService;

    public String convertVoiceToText(MultipartFile file) {
        return sttService.convertVoiceToText(file);
    }
}
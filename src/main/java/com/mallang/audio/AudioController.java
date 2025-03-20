package com.mallang.audio;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/audio")
@RequiredArgsConstructor
public class AudioController {
    private final AudioService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE
        , produces = MediaType.APPLICATION_JSON_VALUE)
    public String convertVoiceToText(@RequestPart("file") MultipartFile file) {
        return service.convertVoiceToText(file);
    }
}
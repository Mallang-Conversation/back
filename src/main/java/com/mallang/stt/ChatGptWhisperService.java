package com.mallang.stt;

import java.io.IOException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatGptWhisperService implements SttService{
    private final WebClient webClient;

    public ChatGptWhisperService(
        @Value("${openai.key}") String apiKey,
        @Value("${openai.stt.url}") String url) {
        webClient = WebClient.builder()
            .baseUrl(url)
            .defaultHeader("Authorization", "Bearer " + apiKey)
            .build();

    }

    @Override
    public String convertVoiceToText(MultipartFile audioFile) {
        try {
            MultipartBodyBuilder builder = new MultipartBodyBuilder();
            builder.part("file", new ByteArrayResource(audioFile.getBytes()) {
                    @Override
                    public String getFilename() {
                        return audioFile.getOriginalFilename();
                    }
                })
                .header("Content-Disposition",
                    "form-data; name=file; filename=" + audioFile.getOriginalFilename())
                .contentType(MediaType.parseMediaType(audioFile.getContentType()));
            builder.part("model", "whisper-1");

            ApiRepose apiRepose = webClient.post()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(ApiRepose.class)
                .block();

            return apiRepose.getText();
        } catch (IOException e) {
            throw new RuntimeException("음성 파일 처리 중 오류 발생", e);
        }
    }

    @Data
    private static class ApiRepose{
        private String text;
    }
}
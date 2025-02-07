package com.template.voicechat.text;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ChatGptTextGenerationService implements TextGenerationService {
    private final WebClient webClient;
    private final String model;

    public ChatGptTextGenerationService(
        @Value("${openai.key}") String apiKey,
        @Value("${openai.url}") String apiUrl,
        @Value("${openai.text-model}") String model) {
        webClient = WebClient.builder()
            .baseUrl(apiUrl+"/v1/chat/completions")
            .defaultHeader("Authorization", "Bearer " + apiKey)
            .build();
        this.model = model;
    }

    @Override
    public String generate(List<ChatMessage> messages) {
        ChatGptChatRequest chatGptChatRequest = new ChatGptChatRequest(model, messages);
        ChatGptChatResponse response = webClient.post()
            .body(BodyInserters.fromValue(chatGptChatRequest))
            .retrieve()
            .bodyToMono(ChatGptChatResponse.class)
            .block();
        System.out.println(response);
        return response
            .choices()
            .get(0)
            .message()
            .content();
    }
}
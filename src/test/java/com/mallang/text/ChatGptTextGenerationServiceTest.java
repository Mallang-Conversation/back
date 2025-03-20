package com.mallang.text;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatGptTextGenerationServiceTest {
    @Autowired
    private ChatGptTextGenerationService chatGptTextGenerationService;

    @Test
    void generate() {
        // Given
        List<ChatMessage> messages = List.of(
            new ChatMessage("user", "Hello!")
        );

        // When
        String response = chatGptTextGenerationService.generate(messages);

        // Then
        assertNotNull(response);
        System.out.println(response);
    }
}
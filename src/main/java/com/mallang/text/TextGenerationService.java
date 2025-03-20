package com.mallang.text;

import java.util.List;

public interface TextGenerationService {
    String generate(List<ChatMessage> messages);
}
package com.mallang.text;

import java.util.List;

public record ChatGptChatRequest(
    String model,
    List<ChatMessage> messages) {
}
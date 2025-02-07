package com.template.voicechat.text;

import java.util.List;

public record ChatGptChatRequest(
    String model,
    List<ChatMessage> messages) {
}
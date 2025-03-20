package com.mallang.text;

import java.util.List;

public record ChatGptChatResponse(
    String id,
    String object,
    long created,
    String model,
    List<Choice> choices
) {

    public record Choice(
        int index,
        ChatMessage message
    ) {
    }
}
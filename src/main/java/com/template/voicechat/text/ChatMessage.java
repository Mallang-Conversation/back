package com.template.voicechat.text;

public record ChatMessage(
    String role,
    String content
) {
}
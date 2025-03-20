package com.mallang.text;

public record ChatMessage(
    String role,
    String content
) {
}
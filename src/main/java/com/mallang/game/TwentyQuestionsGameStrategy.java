package com.mallang.game;

import com.mallang.text.ChatMessage;

public class TwentyQuestionsGameStrategy extends AbstractChatGameStrategy {
  @Override
  public ChatMessage getGamePrompt() {
    return new ChatMessage("assistant", promptLoader.loadPrompt("twenty-questions.txt"));
  }
}
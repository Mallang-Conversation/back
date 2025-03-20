package com.mallang.game;

import com.mallang.text.ChatMessage;

public class FiveLettersGameStrategy extends AbstractChatGameStrategy {
  @Override
  protected ChatMessage getGamePrompt() {
    return new ChatMessage("assistant", promptLoader.loadPrompt("five-letters.txt"));
  }
}

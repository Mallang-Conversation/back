package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;

public class FiveLettersGameStrategy extends AbstractChatGameStrategy {
  @Override
  protected ChatMessage getGamePrompt() {
    return new ChatMessage("assistant", promptLoader.loadPrompt("five-letters.txt"));
  }
}

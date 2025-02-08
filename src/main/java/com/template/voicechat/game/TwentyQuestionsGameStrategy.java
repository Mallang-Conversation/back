package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;

public class TwentyQuestionsGameStrategy extends AbstractChatGameStrategy {
  @Override
  public ChatMessage getGamePrompt() {
    return new ChatMessage("assistant", promptLoader.loadPrompt("twenty-questions.txt"));
  }
}
package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;
import java.util.*;

public abstract class AbstractChatGameStrategy implements ChatGameStrategy {
  protected final PromptLoader promptLoader = new PromptLoader();

  @Override
  public List<ChatMessage> initializeChat() {
    List<ChatMessage> messages = new ArrayList<>();
    messages.add(getCommonPrompt());
    messages.add(getGamePrompt());
    return messages;
  }

  private ChatMessage getCommonPrompt() {
    String content = promptLoader.loadPrompt("common.txt");
    return new ChatMessage("assistant", content);
  }

  protected abstract ChatMessage getGamePrompt();
}
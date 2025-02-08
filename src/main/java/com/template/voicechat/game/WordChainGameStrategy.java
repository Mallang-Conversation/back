package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;

public class WordChainGameStrategy extends AbstractChatGameStrategy {
  @Override
  public ChatMessage getGameMessages() {
    return new ChatMessage("assistant", promptLoader.loadPrompt("word-chain.txt"));
  }
}
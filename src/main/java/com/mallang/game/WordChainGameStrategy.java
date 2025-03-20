package com.mallang.game;

import com.mallang.text.ChatMessage;

public class WordChainGameStrategy extends AbstractChatGameStrategy {
  @Override
  public ChatMessage getGamePrompt() {
    return new ChatMessage("assistant", promptLoader.loadPrompt("word-chain.txt"));
  }
}
package com.mallang.game;

import java.util.*;

public class ChatGameStrategyFactory {
  private static final Map<GameType, ChatGameStrategy> strategies = new EnumMap<>(GameType.class);
  static {
    strategies.put(GameType.WORD_CHAIN, new WordChainGameStrategy());
    strategies.put(GameType.TWENTY_QUESTIONS, new TwentyQuestionsGameStrategy());
    strategies.put(GameType.FIVE_LETTERS, new FiveLettersGameStrategy());
  }

  public static ChatGameStrategy getStrategy(GameType gameType) {
    ChatGameStrategy strategy = strategies.get(gameType);
    if (strategy == null) {
      throw new IllegalArgumentException("알 수 없는 게임 타입: " + gameType);
    }
    return strategy;
  }
}

package com.template.voicechat.game;

public class ChatGameStrategyFactory {
  public static ChatGameStrategy getStrategy(GameType gameType) {
    return switch (gameType) {
      case WORD_CHAIN -> new WordChainGameStrategy();
      case TWENTY_QUESTIONS -> new TwentyQuestionsGameStrategy();
      case FIVE_LETTERS -> new FiveLettersGameStrategy();
      default -> throw new IllegalArgumentException("알 수 없는 게임 타입: " + gameType);
    };
  }
}

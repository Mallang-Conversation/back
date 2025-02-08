package com.template.voicechat.game;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum GameType {
  WORD_CHAIN("끝말잇기", "앞 사람이 말한 단어의 마지막 글자로 시작하는 단어를 이어가는 게임"),
  TWENTY_QUESTIONS("스무고개", "한 사람이 특정 사물이나 개념을 정하고, 20개의 질문 안에 그것을 맞추는 게임"),
  FIVE_LETTERS("다섯글자 대화", "다섯 글자로 이루어진 단어를 이용해 대화를 이어가는 게임"),
  ;

  private String name;
  private String description;
}

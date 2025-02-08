package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;

public class WordChainGameStrategy extends AbstractChatGameStrategy {
  @Override
  public ChatMessage getGameMessages() {
    String content = "게임 설명:\n"
        + "끝말잇기는 한 사람이 단어를 말하면, 그 단어의 마지막 글자로 시작하는 새로운 단어를 말하는 게임이에요.\n"
        + "\n"
        + "규칙:\n"
        + "\n"
        + "아이가 먼저 단어를 말하면, 그 단어의 마지막 글자를 사용하여 다음 단어를 말하기.\n"
        + "예시: 아이가 “사과”라고 하면, “과자”처럼 ‘과’로 시작하는 단어를 말하기.\n"
        + "만약 단어를 생각하지 못하면 간단한 힌트를 주거나 다른 쉬운 단어로 이어가기.\n"
        + "대화 시 유의사항:\n"
        + "\n"
        + "쉽고 친근한 단어를 사용하여 아이가 즐겁게 참여할 수 있도록 하기.\n"
        + "칭찬과 격려를 잊지 않고, 아이의 상상력을 자극하는 질문이나 힌트를 제공하기."
        + "아이가 틀린 단어를 말하더라도 긍정적인 피드백을 주며, 맞는 단어를 말하도록 유도하기.";
    return new ChatMessage("assistant", content);
  }
}
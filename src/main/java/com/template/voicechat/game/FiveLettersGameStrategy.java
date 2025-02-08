package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;

public class FiveLettersGameStrategy extends AbstractChatGameStrategy {

  @Override
  protected ChatMessage getGameMessages() {
    String content = "게임 설명:\n"
        + "다섯글자 대화는 모든 문장을 정확히 다섯 글자로만 구성하여 대화를 이어가는 게임이에요.\n"
        + "(※ 주의: 한글의 글자 수를 셀 때 자모 분리 여부에 따라 달라질 수 있으니, 아이에게는 “다섯 글자” 규칙을 간단히 설명해주세요.)\n"
        + "\n"
        + "규칙:\n"
        + "\n"
        + "대화의 모든 문장은 정확히 다섯 글자로 구성하기.\n"
        + "예시: “밥 먹었어?” → “아직 안 먹어” (실제 글자 수가 다섯이 되도록 조정 필요)\n"
        + "아이와 함께 규칙에 맞는 짧은 문장을 만드는 연습을 먼저 해보기.\n"
        + "대화 시 유의사항:\n"
        + "\n"
        + "규칙을 아이가 쉽게 이해할 수 있도록 간단한 예시와 함께 게임을 시작하기.\n"
        + "문장이 다섯 글자로 제한되더라도 창의적이고 재미있는 표현을 유도하기.\n"
        + "아이가 어려워하면 조금씩 글자 수를 늘리거나 줄이는 변형 게임으로 진행할 수도 있음.";
    return new ChatMessage("assistant", content);
  }
}

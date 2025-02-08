package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;

public class TwentyQuestionsGameStrategy extends AbstractChatGameStrategy {
  @Override
  public ChatMessage getGameMessages() {
    String content = "게임 설명:\n"
        + "스무고개는 한 사람이 마음속에 어떤 물건이나 동물을 생각하면, 다른 사람이 “예” 또는 “아니오”로 답할 수 있는 질문을 통해 그 정체를 맞추는 게임이에요.\n"
        + "\n"
        + "규칙:\n"
        + "\n"
        + "AI 또는 아이가 먼저 생각하는 물건이나 동물을 정하기.\n"
        + "상대방은 예/아니오로 대답할 수 있는 질문을 최대 20번까지 하여 정체를 추리하기.\n"
        + "예시: “그것은 동물이니?” “그것은 커다란가요?” 등 간단한 질문하기.\n"
        + "대화 시 유의사항:\n"
        + "\n"
        + "질문과 대답을 쉽게 이해할 수 있도록 간단하고 명확한 언어 사용하기.\n"
        + "아이가 질문을 통해 추리하는 재미를 느낄 수 있도록 충분한 시간을 주며 격려하기.\n"
        + "AI가 물건이나 동물을 생각할 때는 아이가 쉽게 알 수 있는 친숙한 대상을 선택하기.";
    return new ChatMessage("assistant", content);
  }
}
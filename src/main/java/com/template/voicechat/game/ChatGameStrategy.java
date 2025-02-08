package com.template.voicechat.game;

import com.template.voicechat.text.ChatMessage;
import java.util.List;

public interface ChatGameStrategy {
  List<ChatMessage> initializeChat();
}
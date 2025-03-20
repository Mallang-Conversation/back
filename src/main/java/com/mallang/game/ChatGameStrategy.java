package com.mallang.game;

import com.mallang.text.ChatMessage;
import java.util.List;

public interface ChatGameStrategy {
  List<ChatMessage> initializeChat();
}
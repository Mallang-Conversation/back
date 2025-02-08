package com.template.voicechat.chat;

import com.template.voicechat.game.*;
import com.template.voicechat.text.*;
import java.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {
  private final ChatSessionManager sessionManager;
  private final TextGenerationService textGenerationService;

  public void startChat(String sessionId, GameType gameType) {
    List<ChatMessage> messages = new ArrayList<>(
        ChatGameStrategyFactory.getStrategy(gameType).initializeChat());
    sessionManager.add(sessionId, messages);
  }

  public void endChat(String sessionId) {
    sessionManager.remove(sessionId);
  }

  public String sendMessage(String id, String payload) {
    List<ChatMessage> messages = sessionManager.getMessages(id);
    messages.add(new ChatMessage("user", payload));
    String response = textGenerationService.generate(messages);
    messages.add(new ChatMessage("assistant", response));
    return response;
  }
}
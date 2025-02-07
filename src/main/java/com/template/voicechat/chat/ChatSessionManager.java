package com.template.voicechat.chat;

import com.template.voicechat.text.ChatMessage;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ChatSessionManager {
    private final Map<String,List<ChatMessage>> sessionMap = new ConcurrentHashMap<>();

    public void add(String sessionId, List<ChatMessage> messages) {
        sessionMap.put(sessionId, messages);
    }

    public void remove(String sessionId) {
        sessionMap.remove(sessionId);
    }

    public boolean contains(String sessionId) {
        return sessionMap.containsKey(sessionId);
    }

    public List<ChatMessage> getMessages(String sessionId) {
        return sessionMap.get(sessionId);
    }
}
package com.template.voicechat.chat;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

@Component
public class ChatSessionManager {
    private final Set<String> sessionIds = ConcurrentHashMap.newKeySet();

    public void add(String sessionId) {
        sessionIds.add(sessionId);
    }

    public void remove(String sessionId) {
        sessionIds.remove(sessionId);
    }

    public boolean contains(String sessionId) {
        return sessionIds.contains(sessionId);
    }
}
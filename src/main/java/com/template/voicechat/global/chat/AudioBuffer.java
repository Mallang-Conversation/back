package com.template.voicechat.global.chat;

import java.io.*;
import java.util.concurrent.ScheduledFuture;

public class AudioBuffer {
    private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private ScheduledFuture<?> scheduledTask;

    public synchronized void append(byte[] data) throws IOException {
        buffer.write(data);
    }

    public synchronized byte[] getData() {
        return buffer.toByteArray();
    }

    public synchronized void clear() {
        buffer.reset();
    }

    public synchronized void setScheduledTask(ScheduledFuture<?> task) {
        this.scheduledTask = task;
    }

    public synchronized ScheduledFuture<?> getScheduledTask() {
        return scheduledTask;
    }
}
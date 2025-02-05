package com.template.voicechat.global.chat;

import java.io.*;
import java.nio.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

@Component
@Slf4j
public class VoiceWebSocketHandler extends BinaryWebSocketHandler {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final Map<String, AudioBuffer> sessionBuffers = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        log.info("새로운 WebSocket 연결 수립: {}", session.getId());
        sessionBuffers.put(session.getId(), new AudioBuffer());
    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message){
        AudioBuffer audioBuffer = sessionBuffers.get(session.getId());
        if (audioBuffer == null) {
            log.warn("버퍼가 없는 세션: {}", session.getId());
            return;
        }

        try {
            byte[] audioData = message.getPayload().array();
            audioBuffer.append(audioData);
            log.info("세션 {}: 수신한 음성 데이터 ({} 바이트)", session.getId(), audioData.length);

            // 기존에 예약된 작업이 있다면 취소
            ScheduledFuture<?> existingTask = audioBuffer.getScheduledTask();
            if (existingTask != null && !existingTask.isDone()) {
                existingTask.cancel(false);
            }
            // 2초 후에 변환 작업 예약 (필요에 따라 1~3초로 조절)
            ScheduledFuture<?> future = scheduler.schedule(() -> {
                processAudioBuffer(session.getId(), audioBuffer.getData());
                audioBuffer.clear();
            }, 2, TimeUnit.SECONDS);
            audioBuffer.setScheduledTask(future);
        } catch (IOException e) {
            log.error("데이터 처리 중 에러 발생: ", e);
        }
    }

    private void processAudioBuffer(String sessionId, byte[] audioData) {
        // PCM 데이터에 대한 기본 파라미터 설정 (필요에 따라 변경)
        final int sampleRate = 16000; // 16kHz
        final int channels = 1;       // Mono
        final int bitsPerSample = 16; // 16-bit PCM

        try {
            // 저장할 WAV 파일 경로 지정 (예: audio_세션ID.wav)
            File wavFile = new File("audio_" + sessionId + ".wav");

            // 파일 출력 스트림 생성
            try (FileOutputStream fos = new FileOutputStream(wavFile)) {
                // WAV 헤더 생성 (44바이트)
                byte[] header = generateWavHeader(audioData.length, sampleRate, channels, bitsPerSample);
                fos.write(header);
                // 누적된 PCM 데이터를 기록
                fos.write(audioData);
            }

            log.info("세션 {}: WAV 파일 생성 완료: {}", sessionId, wavFile.getAbsolutePath());
        } catch (IOException e) {
            log.error("WAV 파일 생성 중 에러 발생: ", e);
        }
    }

    private byte[] generateWavHeader(int audioDataLength, int sampleRate, int channels, int bitsPerSample) {
        int byteRate = sampleRate * channels * bitsPerSample / 8;
        int blockAlign = channels * bitsPerSample / 8;
        int totalDataLen = audioDataLength + 36; // 전체 파일 크기 - 8바이트

        ByteBuffer buffer = ByteBuffer.allocate(44);
        buffer.order(ByteOrder.LITTLE_ENDIAN);

        // "RIFF" Chunk Descriptor
        buffer.put("RIFF".getBytes(StandardCharsets.US_ASCII)); // ChunkID
        buffer.putInt(totalDataLen);                              // ChunkSize
        buffer.put("WAVE".getBytes(StandardCharsets.US_ASCII));   // Format

        // "fmt " sub-chunk
        buffer.put("fmt ".getBytes(StandardCharsets.US_ASCII));   // Subchunk1ID
        buffer.putInt(16);                                        // Subchunk1Size for PCM
        buffer.putShort((short) 1);                               // AudioFormat (1 = PCM)
        buffer.putShort((short) channels);                        // NumChannels
        buffer.putInt(sampleRate);                                // SampleRate
        buffer.putInt(byteRate);                                  // ByteRate
        buffer.putShort((short) blockAlign);                      // BlockAlign
        buffer.putShort((short) bitsPerSample);                   // BitsPerSample

        // "data" sub-chunk
        buffer.put("data".getBytes(StandardCharsets.US_ASCII));   // Subchunk2ID
        buffer.putInt(audioDataLength);                           // Subchunk2Size

        return buffer.array();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status){
        log.info("WebSocket 연결 종료: {} (상태: {})", session.getId(), status);
        sessionBuffers.remove(session.getId());
    }
}
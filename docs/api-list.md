# 사용된 API 목록

## 1. **채팅 연결- WebSocket**
- 사용 목적: 실시간 음성 대화를 위한 WebSocket 연결
- 엔드포인트: `ws://localhost:8081/ws/chat?game=게임이름`
- 게인이름 :
  - WORD_CHAIN : 끝말잇기
  - TWENTY_QUESTIONS : 스무고개
  - FIVE_LETTERS : 다섯 글자 대화
- 전송 데이터:
    - 텍스트 메시지 (`ws.send(message)`)

---


## 2. **STT 변환 - HTTP Request**
- 사용 목적: 녹음된 음성을 서버로 전송
- 엔드포인트: `http://localhost:8081/api/v1/audio`
- HTTP 메서드: `POST`
- 요청 데이터:
    - `FormData`에 WAV 파일 (`file: audio.wav`)
- 응답 처리:
    - JSON 파싱 후 자동으로 입력 필드에 결과를 반영 (`messageInput.value = data;`)

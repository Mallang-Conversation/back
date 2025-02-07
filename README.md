# 🗣️ AI를 이용한 실시간 대화 서비스

이 프로젝트는 **AI를 활용한 실시간 대화 서비스**로, 사용자가 웹 브라우저에서 음성 또는 텍스트를 통해 AI와 자연스럽게 소통할 수 있도록 설계되었습니다.

---

## 🏗️ 시스템 구조 및 주요 기능

### **클라이언트 (웹 브라우저)**
- `resources/static/voice.html`에서 실행되는 웹 애플리케이션
- **WebSocket**을 통해 서버와 실시간 연결
- 마이크를 통해 사용자 음성을 녹음하고 **API를 통해 서버로 전송**
- 서버로부터 **텍스트 및 음성 응답을 받아 채팅창에 표시**
- **TTS(Text-to-Speech)** 를 활용하여 음성 응답을 재생

### **서버 (Spring Boot)**
- **WebSocket 서버**를 운영하여 클라이언트와 실시간 데이터 교환
- **STT(Speech-to-Text) API**를 활용하여 음성을 텍스트로 변환
- **Text Generator API**를 호출하여 응답을 생성

---

## 🔄 실시간 대화 흐름

각 단계는 클라이언트와 서버 간의 상호작용을 나타내며, 사용된 이모지는 해당 단계의 주요 기능을 상징합니다.

1️⃣ 연결 설정
- 🌐 클라이언트: WebSocket을 통해 서버와 실시간 연결을 수립

2️⃣ 음성 녹음
- 🎤 클라이언트: 사용자의 음성을 마이크로 녹음 시작

3️⃣ 음성 데이터 전송
- 🚀 클라이언트: 녹음된 음성 데이터를 API를 통해 서버로 전송

4️⃣ STT 처리
- 🤖 서버: 수신된 음성을 STT API를 사용해 텍스트로 변환
- ✍️ 결과: 변환된 텍스트

5️⃣ 텍스트 전달
- 📩 서버 → 클라이언트: 변환된 텍스트를 클라이언트로 전송

6️⃣ 텍스트 재전송 (선택적 단계)

🔄 클라이언트: 필요에 따라, 수신한 텍스트를 다시 WebSocket을 통해 서버에 전송

7️⃣ 응답 생성
- 💡 서버: 클라이언트의 텍스트를 기반으로 Text Generator API를 호출하여 응답 생성

8️⃣ 응답 전달
- 📤 서버 → 클라이언트: 생성된 응답을 클라이언트로 전송

9️⃣ 음성 응답 재생
- 🔊 클라이언트: TTS(Text-to-Speech)를 활용해 응답을 음성으로 변환, 사용자에게 재생
---

## 🔌 인터페이스와 구현체
이 프로젝트는 서버 내 **STT**와 **Text Generator** 기능을 별도의 인터페이스로 추상화하여 구현했습니다. 이를 통해, 현재 ChatGPT의 Whisper (STT) 및 ChatGPT API (Text Generator)를 사용하지만, 앞으로 다른 서비스로 쉽게 대체할 수 있습니다.

### 🎙️ STT (Speech-to-Text) 인터페이스
- 역할: 음성 데이터를 텍스트로 변환하는 기능을 추상화합니다.
  - 🎤 입력: 사용자 음성 데이터
  - 👉 처리: 인터페이스를 통해 전달된 음성 데이터 변환
  - ✍️ 출력: 변환된 텍스트 
- 현재 구현체: ChatGPT의 Whisper API



### 📝 Text Generator 인터페이스
- 역할: 텍스트 데이터를 바탕으로 응답을 생성하는 기능을 추상화합니다.
  - 📝 입력: 사용자 또는 변환된 텍스트
  - 👉 처리: 인터페이스를 통해 전달된 텍스트 기반 응답 생성
  - 💬 출력: 생성된 응답
- 현재 구현체: ChatGPT API

### 🔄 인터페이스 흐름 요약
1. STT 프로세스 : 🎤 음성 입력 → STT 인터페이스 → (현재: Whisper) → 텍스트 출력
2. 텍스트 생성 프로세스 : 📝 텍스트 입력 → Text Generator 인터페이스 → (현재: ChatGPT) → 응답 출력

모듈화 및 인터페이스화를 통해 각 기능을 독립적으로 관리할 수 있어, 시스템 확장 및 유지보수가 용이해집니다.

---

## 🙌 문의
- **개발자:** [송해]
- **문의:** thdgo456@gmail.com
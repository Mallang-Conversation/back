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

## 🤞 사용 방법

### 1. 프로젝트 실행 전 설정
프로젝트를 시작하기 전에 `resources/application.yml` 파일을 열어 OpenAI 관련 설정을 추가해야 합니다.
아래는 예시 구성입니다.

```yaml
openai:
  key: #API-KEY (예: sk-xxxxxxxxxxxxxxxxxxxx)
  stt:
    url: https://api.openai.com/v1/audio/transcriptions
  text-generator:
    url: https://api.openai.com/v1/chat/completions
    model: #text-generator-model (예: gpt-3.5-turbo)
```

- `openai.key`: OpenAI API 키를 입력합니다.
- `openai.text-generator.model`: 사용할 텍스트 생성 모델명을 지정합니다.
모델 종류는 https://platform.openai.com/docs/pricing 에서 확인할 수 있습니다.
<br><br>

### 2. 프로젝트 실행 방법
이 프로젝트는 Java Spring Boot와 Gradle로 구성되어 있습니다.
실행하는 방법은 다음과 같습니다.

**프로젝트 클론**
```bash
git clone https://github.com/SongHae8640/java-spring-real-time-conversation-with-ai.git
```

**프로젝트 디렉터리로 이동**
```bash
cd java-spring-real-time-conversation-with-ai
```

**의존성 설치 및 빌드**
```bash
./gradlew build
```
- Windows 환경에서는 `gradlew.bat build` 명령을 사용합니다.

**프로젝트 실행**
```bash
./gradlew bootRun
```
- 또는 IDE(Spring Tools, IntelliJ 등)에서 메인 클래스를 직접 실행해도 됩니다.
<br><br>

### 3. 사이트 접속 방법
프로젝트가 정상적으로 실행되면, 브라우저에서 다음 주소로 접속합니다.
```bash
http://localhost:8081/voice.html
```

접속 시 아래와 같은 실시간 음성 대화 화면이 표시됩니다.
<br><br>

### 4. 사용 방법
**1) 게임 선택**
- 화면 상단의 게임 선택 드롭다운에서 원하는 게임을 선택합니다.

**2) 대화 시작**
- `대화 시작` 버튼을 누르면 선택한 게임으로 AI와 실시간 대화를 시작할 수 있습니다.

**3) 음성 입력**
- `녹음 시작` 버튼을 클릭하면 마이크로 사용자의 음성을 녹음합니다.
- 말을 마친 뒤 `녹음 중지` 버튼을 누르면, 녹음된 음성이 텍스트로 변환되어 채팅 창에 표시됩니다.

**4) AI 응답**
- 변환된 텍스트를 기반으로 AI가 대답을 생성하여 채팅창에 표시합니다.
- 동시에 TTS(Text-to-Speech)를 통해 음성으로도 재생됩니다.

**5) 텍스트 직접 입력**
- 필요하다면, 직접 메시지를 입력한 후 `전송` 버튼을 누를 수도 있습니다.

**6) 게임 전환**
- 다른 게임을 하고 싶다면, `대화 종료` 버튼으로 현재 대화를 종료한 뒤
- 다시 `게임 선택` 드롭다운에서 게임을 변경하고 `대화 시작`을 누르면 됩니다.

이 과정을 통해, 사용자는 음성 또는 텍스트로 자유롭게 AI와 대화할 수 있습니다.

<p>
  <img src="https://github.com/SongHae8640/java-spring-real-time-conversation-with-ai/blob/master/run.gif">
</p>
---

## 🙌 문의
- **개발자:** [송해]
- **문의:** thdgo456@gmail.com
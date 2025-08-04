# SHOWMETHECS 🎓💡

"SHOWMETHECS"는 Gemini AI와 함께 CS(컴퓨터 과학), Java, Spring 핵심 지식을 학습하고 관리하는 AI 학습 도우미 웹 애플리케이션입니다.

---

## 🚀 프로젝트 개요 (Project Overview)

이 프로젝트는 개인 개발 역량, 특히 백엔드(Spring Boot) 개발 감각과 AI(Gemini API) 활용 능력을 유지하고 향상시키기 위해 시작되었습니다. 취업 준비 과정에서 편하게 CS 및 Java/Spring 핵심 지식을 체계적으로 학습하고 관리하는 데 도움을 주는 것을 목표로 합니다.

## ✨ 주요 기능 (Key Features)

### 현재 구현 목표 (MVP)
* **AI 기반 학습 주제 추천**: 사용자가 선택한 카테고리(CS, Java, Spring)에 따라 Gemini AI가 다음 학습 주제를 추천합니다.
* **핵심 개념 요약 제공**: 추천된 주제에 대한 간결하고 핵심적인 개념 요약을 Gemini AI가 생성하여 제공합니다.
* **예상 질문 리스트**: 학습 내용을 더 깊이 이해하고 스스로 점검할 수 있도록 관련 예상 질문 목록을 제공합니다.

### 향후 확장 계획 (Future Plans)
* **학습 노트 저장 및 관리**: 학습한 내용을 '내 학습 노트'에 저장하고 언제든지 다시 볼 수 있는 마이페이지 기능.
* **어학 공부 탭**: AI 기반의 영어, 일본어 등 어학 학습 기능 확장.

## 🛠️ 기술 스택 (Tech Stack)

* **백엔드**: `Java 17+`, `Spring Boot 3.x.x`
* **프론트엔드**: `Thymeleaf`, `HTML5`, `CSS3`
* **AI 모델**: `Google Gemini 2.0 Flash API`
* **빌드 도구**: `Gradle`
* **데이터베이스**: (향후 저장 기능 구현 시) `H2 Database` (개발용), `MySQL` (배포용)

## ⚙️ 설치 및 실행 방법 (Setup & Run)

1.  **프로젝트 복제**:
    ```bash
    git clone [당신의 GitHub 저장소 URL]
    cd SHOWMETHECS
    ```

2.  **Google Gemini API Key 설정**:
    * [Google AI Studio](https://aistudio.google.com/app/apikey)에서 Gemini API 키를 발급받으세요.
    * 발급받은 API 키는 **절대 코드에 직접 노출하지 마세요.** 다음 방법 중 하나를 선택하여 설정합니다.
        * **방법 1 (권장 - 환경 변수):** 시스템 환경 변수로 `GEMINI_API_KEY`를 설정합니다.
            ```bash
            # Linux/macOS
            export GEMINI_API_KEY="YOUR_API_KEY"
            # Windows (CMD)
            set GEMINI_API_KEY="YOUR_API_KEY"
            # Windows (PowerShell)
            $env:GEMINI_API_KEY="YOUR_API_KEY"
            ```
        * **방법 2 (Spring `application.properties`):** `src/main/resources/application.properties` 파일에 다음 라인을 추가합니다. (이 방법은 민감한 정보를 Git에 올리지 않도록 `.gitignore`에 `application.properties` 또는 관련 설정을 추가하는 것이 중요합니다.)
            ```properties
            gemini.api.key=YOUR_API_KEY
            ```
            **주의**: Git에 업로드하지 않도록 `.gitignore` 파일에 `application.properties`를 추가하거나, 키는 환경변수로 관리하는 것이 보안에 좋습니다.

3.  **프로젝트 빌드 및 실행**:
    * **Gradle 사용 시**:
        ```bash
        ./gradlew clean build
        ./gradlew bootRun
        ```

4.  **접속**:
    애플리케이션이 성공적으로 시작되면, 웹 브라우저에서 `http://localhost:8080` (기본 포트)으로 접속합니다.

---

## 🤝 기여 (Contributing)

개인 프로젝트이므로 현재로서는 외부 기여를 받지 않습니다.

---

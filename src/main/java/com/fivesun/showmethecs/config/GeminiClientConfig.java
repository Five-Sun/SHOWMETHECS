package com.fivesun.showmethecs.config;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerationConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiClientConfig {

    @Bean
    public Client geminiClient(@Value("${google.api-key}") String apiKey) {
        return Client.builder()
                .apiKey(apiKey)
                .build();
    }

    @Bean
    public String geminiModel(@Value("${google.model:gemini-pro}") String model) {
        return model;
    }

    @Bean
    public GenerateContentConfig config() {
        return GenerateContentConfig.builder()
                .temperature(0.9f) // 다양성 증가
                .topP(0.95f) // 상위 확률 분포 확장
                .maxOutputTokens(512) // 응답 길이 제한
                .build();
    }
}

package com.fivesun.showmethecs.service;

import com.fivesun.showmethecs.service.PromptLoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiService {
    private final RestClient restClient;
    @Value("${gemini.api-key}")
    private String apiKey;
    private final PromptLoaderService promptLoaderService;

    // 요약 답변을 받는 새로운 메서드
    public String getSummary(String category) {
        String prompt = promptLoaderService.loadPrompt(category + "_summary");
        return callGeminiApi(prompt);
    }

    // 상세 답변을 받는 새로운 메서드
    public String getDetail(String category, String topic) {
        String promptTemplate = promptLoaderService.loadPrompt(category + "_detail");
        String prompt = "주제는 \"" + topic + "\"이야. " + promptTemplate;
        return callGeminiApi(prompt);
    }

    // Gemini API 호출 로직을 분리한 도우미 메서드
    private String callGeminiApi(String prompt) {
        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", Collections.singletonList(part));
        Map<String, Object> requestBody = Map.of("contents", Collections.singletonList(content));

        Map response = restClient.post()
                .uri("/v1/models/gemini-pro:generateContent?key=" + apiKey)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(requestBody)
                .retrieve()
                .body(Map.class);

        if (response != null && response.containsKey("candidates")) {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            if (!candidates.isEmpty()) {
                Map<String, Object> candidate = candidates.get(0);
                Map<String, Object> contentPart = (Map<String, Object>) candidate.get("content");
                List<Map<String, Object>> parts = (List<Map<String, Object>>) contentPart.get("parts");
                if (!parts.isEmpty()) {
                    return (String) parts.get(0).get("text");
                }
            }
        }
        return "죄송합니다. 답변을 생성하는 중에 오류가 발생했습니다.";
    }

}
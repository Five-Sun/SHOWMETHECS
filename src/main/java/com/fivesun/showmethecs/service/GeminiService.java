package com.fivesun.showmethecs.service;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.GenerateContentResponse;
import com.google.genai.types.GenerationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GeminiService {

    private final Client client;
    private final String geminiModel;
    private final GenerateContentConfig config;

    public String generate(String prompt) {

        GenerateContentResponse response = client.models.generateContent(
                geminiModel,
                prompt,
                config
        );
        return response.text();
    }

}
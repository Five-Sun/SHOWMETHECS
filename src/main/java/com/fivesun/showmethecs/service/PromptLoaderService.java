package com.fivesun.showmethecs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class PromptLoaderService {

    private final ResourceLoader resourceLoader;

    public String loadPrompt(String category) {
        String filePath = String.format("classpath:prompts/%s.txt", category);
        Resource resource = resourceLoader.getResource(filePath);
        try {
            return resource.getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load prompt file for category");
        }
    }
}

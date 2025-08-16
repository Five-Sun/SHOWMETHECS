package com.fivesun.showmethecs.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class PromptLoaderService {

    public String loadPrompt(String name) throws IOException {
        Path path = new ClassPathResource("prompts/" + name + ".txt").getFile().toPath();
        return Files.readString(path, StandardCharsets.UTF_8);
    }
}

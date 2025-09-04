package com.fivesun.showmethecs.domain.ai.controller;

import com.fivesun.showmethecs.domain.ai.service.GeminiService;
import com.fivesun.showmethecs.domain.ai.service.PromptLoaderService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class LearningController {

    private final PromptLoaderService promptLoaderService;
    private final GeminiService geminiService;

    @GetMapping("/category")
    public String category() {
        return "category-selection";
    }

    @GetMapping("/learning-summary")
    public String summary(@RequestParam String category, Model model, HttpSession session) {
        try {
            String prompt = promptLoaderService.loadPrompt(category + "_summary");
            String answer = geminiService.generate(prompt);

            // 세션에 요약 전체 텍스트 저장
            session.setAttribute("category", category);
            session.setAttribute("topicSummary", answer);
            model.addAttribute("answer", answer);
        } catch (Exception e) {
            model.addAttribute("answer", "요약 생성 오류: " + e.getMessage());
        }
        return "learning-page";
    }

    @GetMapping("/learning-detail")
    public String detail(HttpSession session, Model model) {
        String category = (String) session.getAttribute("category");
        String topicSummary = (String) session.getAttribute("topicSummary");

        if (category == null || topicSummary == null) {
            model.addAttribute("answer", "카테고리 또는 주제가 누락되었습니다.");
            return "learning-page";
        }

        try {
            String base = promptLoaderService.loadPrompt(category + "_detail");

            // 🎯 오늘의 랜덤 주제 추출
            String subject = Arrays.stream(topicSummary.split("\n"))
                    .filter(line -> line.startsWith("🎯"))
                    .map(line -> line.replace("🎯 오늘의 랜덤 주제:", "").trim())
                    .findFirst()
                    .orElse("주제 없음");

            // ✅ 키워드들 추출
            String keywords = Arrays.stream(topicSummary.split("\n"))
                    .filter(line -> line.startsWith("✅"))
                    .map(String::trim)
                    .collect(Collectors.joining("\n"));

            // detail 프롬프트에 주제 + 키워드 함께 전달
            String fullPrompt = base
                    + "\n\n📌 주제: " + subject
                    + "\n📌 반드시 설명에 포함할 키워드:\n" + keywords;

            String answer = geminiService.generate(fullPrompt);
            model.addAttribute("answer", answer);
        } catch (Exception e) {
            model.addAttribute("answer", "상세 생성 오류: " + e.getMessage());
        }

        return "learning-page";
    }
}

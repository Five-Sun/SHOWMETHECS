package com.fivesun.showmethecs.controller;

import com.fivesun.showmethecs.service.GeminiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"topic", "category"})
public class LearningController {

    private final GeminiService geminiService;

    @GetMapping("/category")
    public String showCategorySelection() {
        return "category-selection";
    }

    //1단계: 카테고리 선택 후, 요약 답변을 받는 메서드
    @GetMapping("/learning-summary")
    public String showLearningPage(@RequestParam String category,
                                   Model model) {
        // 세션에 카테고리 저장
        model.addAttribute("category", category);

        //GeminiService를 호출하여 요약 답변을 받음
        String summary = geminiService.getSummary(category);

        //응답에서 주제 추출
        String topic = getTopicFromSummary(summary);

        //세션에 주제 저장
        model.addAttribute("topic", topic);
        model.addAttribute("summary", summary);

        return "learning-page";
    }

    //2단계: "준비 완료" 버튼 클릭 후, 상세 답변을 받는 메서드
    @GetMapping("/learning-detail")
    public String getLearningDetail(Model model) {
        // 세션에서 카테고리와 주제를 가져옴
        String category = (String) model.getAttribute("category");
        String topic = (String) model.getAttribute("topic");

        // GeminiService를 호출하여 상세 답변을 받음
        String detail = geminiService.getDetail(category, topic);
        model.addAttribute("detail", detail);

        return "learning-page";
    }

    // 요약 답변에서 주제를 추출하는 도우미 메서드
    private String getTopicFromSummary(String summary) {
        if (summary != null && summary.contains("🎯 오늘의 랜덤 주제:")) {
            int startIndex = summary.indexOf("🎯 오늘의 랜덤 주제:") + "🎯 오늘의 랜덤 주제:".length();
            int endIndex = summary.indexOf("\n", startIndex);
            return summary.substring(startIndex, endIndex).trim();
        }
        return "알 수 없는 주제";
    }
}

package com.fivesun.showmethecs.controller;

import com.fivesun.showmethecs.domain.user.UserProfile;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/")
    public String indexOrMainPage(Authentication authentication,
                                  Model model) {
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof DefaultOAuth2User principal) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) principal.getAttributes().get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            UserProfile userProfile = new UserProfile(
                    (String) profile.get("nickname"),
                    (String) profile.get("profile_image_url")
            );
            model.addAttribute("userProfile", userProfile);
            return "main-page";
        }
        return "index-page";
    }
    @GetMapping("/main-page")
    public String mainPage(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof DefaultOAuth2User principal) {

            Map<String, Object> kakaoAccount = (Map<String, Object>) principal.getAttributes().get("kakao_account");
            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

            UserProfile userProfile = new UserProfile(
                    (String) profile.get("nickname"),
                    (String) profile.get("profile_image_url")
            );

            model.addAttribute("userProfile", userProfile);
        }
        return "main-page";
    }
}

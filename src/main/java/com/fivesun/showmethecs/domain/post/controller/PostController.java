package com.fivesun.showmethecs.domain.post.controller;

import com.fivesun.showmethecs.domain.post.dto.PostDto;
import com.fivesun.showmethecs.domain.post.entity.Post;
import com.fivesun.showmethecs.domain.post.service.PostService;
import com.fivesun.showmethecs.domain.user.entity.User;
import com.fivesun.showmethecs.domain.user.service.UserService;
import com.fivesun.showmethecs.global.constant.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id,
                         @AuthenticationPrincipal DefaultOAuth2User principal,
                         Model model) {
        Post post = postService.findById(id);

        PostDto dto = new PostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor().getNickname(),
                post.getAuthor().getEmail(),
                post.getCreatedAt()
        );

        model.addAttribute("post", dto);

        String currentUserEmail = null;
        if (principal != null) {
            Map<String, Object> kakaoAccount = (Map<String, Object>) principal.getAttributes().get("kakao_account");
            currentUserEmail = (String) kakaoAccount.get("email");
        }

        boolean editable = (currentUserEmail != null & currentUserEmail.equals(dto.getAuthorEmail()));
        model.addAttribute("editable", editable);

        return "post/detail";
    }

    @GetMapping("/new")
    public String createForm() {
        return "post/form";
    }

    @PostMapping
    public String create(@RequestParam String title,
                         @RequestParam String content,
                         @AuthenticationPrincipal DefaultOAuth2User principal) {

        String providerId = String.valueOf(principal.getAttributes().get("id"));

        User user = userService.findByProviderAndProviderId(Provider.KAKAO, providerId);

        postService.create(title, content, user);

        return "redirect:/posts";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Post post = postService.findById(id);
        model.addAttribute("post", post);
        return "post/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         String title,
                         String content,
                         @AuthenticationPrincipal DefaultOAuth2User principal) {
        String providerId = String.valueOf(principal.getAttributes().get("id"));
        User user = userService.findByProviderAndProviderId(Provider.KAKAO, providerId);

        postService.update(id, title, content, user);
        return "/redirect:/posts/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @AuthenticationPrincipal DefaultOAuth2User principal) {
        String providerId = String.valueOf(principal.getAttributes().get("id"));
        User user = userService.findByProviderAndProviderId(Provider.KAKAO, providerId);

        postService.delete(id, user);
        return "redirect:/posts";
    }
}

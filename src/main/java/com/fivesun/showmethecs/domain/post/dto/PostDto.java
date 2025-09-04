package com.fivesun.showmethecs.domain.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String authorNickname;
    private String authorEmail;
    private LocalDateTime createdAt;
}

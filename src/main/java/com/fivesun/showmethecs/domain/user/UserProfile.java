package com.fivesun.showmethecs.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserProfile {
    private final String nickname;
    private final String profileImage;
}

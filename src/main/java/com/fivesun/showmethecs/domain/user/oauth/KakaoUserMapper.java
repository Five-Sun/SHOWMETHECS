package com.fivesun.showmethecs.domain.user.oauth;

import com.fivesun.showmethecs.domain.user.entity.User;
import com.fivesun.showmethecs.global.constant.Provider;
import com.fivesun.showmethecs.global.constant.Role;

import java.util.Map;

public class KakaoUserMapper {

    public static User toEntity(Map<String, Object> attributes) {
        String kakaoId = String.valueOf(attributes.get("id"));
        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        String email = (String) kakaoAccount.get("email");

        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
        String nickname = (String) profile.get("nickname");
        String profileImage = (String) profile.get("profile_image_url");

        return User.builder()
                .provider(Provider.KAKAO)
                .providerId(kakaoId)
                .email(email)
                .nickname(nickname)
                .profileImage(profileImage)
                .role(Role.ROLE_USER)
                .build();
    }
}

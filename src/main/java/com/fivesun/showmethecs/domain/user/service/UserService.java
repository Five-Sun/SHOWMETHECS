package com.fivesun.showmethecs.domain.user.service;

import com.fivesun.showmethecs.domain.user.entity.User;
import com.fivesun.showmethecs.domain.user.repository.UserRepository;
import com.fivesun.showmethecs.global.constant.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public User saveOrUpdate(User user) {
        return userRepository.findByProviderAndProviderId(user.getProvider(), user.getProviderId())
                .map(existing -> {
                    existing.updateProfile(user.getEmail(), user.getNickname(), user.getProfileImage());
                    return existing;
                })
                .orElseGet(() -> userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public User findByProviderAndProviderId(Provider provider, String providerId) {
        return userRepository.findByProviderAndProviderId(provider, providerId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + providerId));
    }
}

package com.fivesun.showmethecs.domain.user.repository;

import com.fivesun.showmethecs.domain.user.entity.User;
import com.fivesun.showmethecs.global.constant.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByProviderAndProviderId(Provider provider, String providerId);
}

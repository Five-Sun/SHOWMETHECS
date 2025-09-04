package com.fivesun.showmethecs.domain.post.entity;

import com.fivesun.showmethecs.domain.user.entity.User;
import com.fivesun.showmethecs.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 작성자
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

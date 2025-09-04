package com.fivesun.showmethecs.domain.post.service;

import com.fivesun.showmethecs.domain.post.entity.Post;
import com.fivesun.showmethecs.domain.post.repository.PostRepository;
import com.fivesun.showmethecs.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found: " + id));
    }

    public Post create(String title, String content, User author) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        return postRepository.save(post);
    }

    public Post update(Long id, String title, String content, User currentUser) {
        Post post = findById(id);

        if (!post.getAuthor().getId().equals(currentUser.getId())) {
            throw new SecurityException("본인 글만 수정할 수 있습니다.");
        }

        post.update(title, content);
        return post;
    }

    public void delete(Long id, User currentUser) {
        Post post = findById(id);

        if (post != null && !post.getAuthor().getId().equals(currentUser.getId())) {
            throw new SecurityException("본인 글만 삭제할 수 있습니다.");
        }
        postRepository.deleteById(id);
    }
}

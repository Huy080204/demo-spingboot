package com.example.demo.service;

import com.example.demo.dto.request.PostCreateRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class PostService {

    PostRepository postRepository;
    PostMapper postMapper;

    public Post createPost(User user, PostCreateRequest request) {
        Post post = postMapper.toPost(request);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        user.getPosts().add(savedPost);
        return savedPost;
    }

}

package com.example.demo.service.impl;

import com.example.demo.dto.request.post.PostCreateRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    PostRepository postRepository;
    PostMapper postMapper;

    @Override
    public Post createPost(User user, PostCreateRequest request) {
        Post post = postMapper.toPost(request);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        user.getPosts().add(savedPost);
        return savedPost;
    }

}

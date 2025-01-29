package com.example.demo.service;

import com.example.demo.dto.request.PostCreateRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostMapper postMapper;

    public Post createPost(User user, PostCreateRequest request) {
        Post post = postMapper.toPost(request);
        post.setUser(user);
        Post savedPost = postRepository.save(post);
        user.getPosts().add(savedPost);
        return savedPost;
    }

}

package com.example.demo.service;

import com.example.demo.dto.request.post.PostCreateRequest;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;

public interface PostService {

    Post createPost(User user, PostCreateRequest request);

}

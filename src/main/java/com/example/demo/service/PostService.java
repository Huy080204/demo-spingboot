package com.example.demo.service;

import com.example.demo.form.post.CreatePostForm;
import com.example.demo.model.entity.Post;
import com.example.demo.model.entity.User;

public interface PostService {

    Post createPost(User user, CreatePostForm request);

}

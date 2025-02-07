package com.example.demo.mapper;

import com.example.demo.form.post.CreatePostForm;
import com.example.demo.model.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(CreatePostForm request);
}

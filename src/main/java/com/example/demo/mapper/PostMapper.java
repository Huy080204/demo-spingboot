package com.example.demo.mapper;

import com.example.demo.dto.request.post.PostCreateRequest;
import com.example.demo.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostCreateRequest request);
}

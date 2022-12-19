package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.payloads.PostDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,String userId,String categoryId);
    PostDto updatePost(PostDto postDto,String postId);
    void deletePost(String postId);
    List<PostDto> getAllPost();
    PostDto getPostById(String postId);
    List<PostDto> getPostByCategory(String categoryId);
    List<PostDto> getPostByUser(String userId);
    List<PostDto> searchPosts(String keywords);
}

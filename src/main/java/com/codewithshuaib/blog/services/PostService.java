package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.payloads.PostDto;
import com.codewithshuaib.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,String userId,String categoryId);
    PostDto updatePost(PostDto postDto,String postId);
    void deletePost(String postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection);
    PostDto getPostById(String postId);
    PostResponse getPostByCategory(String categoryId,Integer pageNumber, Integer pageSize);
    PostResponse getPostByUser(String userId,Integer pageNumber, Integer pageSize);
    List<PostDto> searchPosts(String keywords);
}

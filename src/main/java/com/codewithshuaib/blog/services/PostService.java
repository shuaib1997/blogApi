package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.payloads.PostDto;
import com.codewithshuaib.blog.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto,Long userId,Long categoryId);
    PostDto updatePost(PostDto postDto,Long postId);
    void deletePost(Long postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection);
    PostDto getPostById(Long postId);
    PostResponse getPostByCategory(Long categoryId,Integer pageNumber, Integer pageSize);
    PostResponse getPostByUser(Long userId,Integer pageNumber, Integer pageSize);
    List<PostDto> searchPosts(String keywords);
}

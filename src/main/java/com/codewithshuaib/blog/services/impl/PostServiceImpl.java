package com.codewithshuaib.blog.services.impl;

import com.codewithshuaib.blog.entities.Category;
import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.entities.User;
import com.codewithshuaib.blog.exceptions.ResourceNotFoundException;
import com.codewithshuaib.blog.payloads.PostDto;
import com.codewithshuaib.blog.repositories.CategoryRepository;
import com.codewithshuaib.blog.repositories.PostRepository;
import com.codewithshuaib.blog.repositories.UserRepository;
import com.codewithshuaib.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PostDto createPost(PostDto postDto,String userId,String categoryId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id",categoryId));
        Post post=this.modelMapper.map(postDto,Post.class);
        post.setImageName("deafult.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost= this.postRepository.save(post);
        return this.modelMapper.map(newPost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, String postId) {
        return null;
    }

    @Override
    public void deletePost(String postId) {

    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public PostDto getPostById(String postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(String categoryId) {
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","ID",categoryId));
        List<Post> posts=this.postRepository.findByCategory(category);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(String userId) {
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
        List<Post> posts=this.postRepository.findByUser(user);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keywords) {
        return null;
    }
}

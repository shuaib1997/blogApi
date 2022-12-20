package com.codewithshuaib.blog.services.impl;

import com.codewithshuaib.blog.entities.Category;
import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.entities.User;
import com.codewithshuaib.blog.exceptions.ResourceNotFoundException;
import com.codewithshuaib.blog.payloads.PostDto;
import com.codewithshuaib.blog.payloads.PostResponse;
import com.codewithshuaib.blog.repositories.CategoryRepository;
import com.codewithshuaib.blog.repositories.PostRepository;
import com.codewithshuaib.blog.repositories.UserRepository;
import com.codewithshuaib.blog.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        post.setPostTitle(postDto.getPostTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost=this.postRepository.save(post);
        return this.modelMapper.map(updatedPost,PostDto.class);
    }

    @Override
    public void deletePost(String postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
        this.postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDirection) {
        Sort sort=sortDirection.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePosts= this.postRepository.findAll(pageable);
        List<Post> posts=pagePosts.getContent();
        List<PostDto> postDtos= posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());

    return postResponse;
    }

    @Override
    public PostDto getPostById(String postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","ID",postId));
        return this.modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostResponse getPostByCategory(String categoryId,Integer pageNumber, Integer pageSize) {
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        Category category=this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","ID",categoryId));
        Page<Post> pagePosts=this.postRepository.findByCategory(category,pageable);
        List<Post> posts=pagePosts.getContent();
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getPostByUser(String userId,Integer pageNumber, Integer pageSize) {
        Pageable pageable=PageRequest.of(pageNumber,pageSize);
        User user=this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","ID",userId));
        Page<Post> pagePosts=this.postRepository.findByUser(user,pageable);
        List<Post> posts=pagePosts.getContent();
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
    }

    @Override
    public List<PostDto> searchPosts(String keywords) {
        List<Post> posts= this.postRepository.findByPostTitleContaining(keywords);
        List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }
}

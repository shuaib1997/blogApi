package com.codewithshuaib.blog.controllers;

import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.payloads.PostDto;
import com.codewithshuaib.blog.repositories.CategoryRepository;
import com.codewithshuaib.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable String userId,
                                              @PathVariable String categoryId){

        PostDto cratedPostDto=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(cratedPostDto, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser( @PathVariable String userId){
        List<PostDto> postDtos=this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory( @PathVariable String categoryId){
        List<PostDto> postDtos=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtos, HttpStatus.OK);
    }



}

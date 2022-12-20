package com.codewithshuaib.blog.controllers;

import com.codewithshuaib.blog.config.AppConstants;
import com.codewithshuaib.blog.payloads.PostDto;
import com.codewithshuaib.blog.payloads.PostResponse;
import com.codewithshuaib.blog.repositories.CategoryRepository;
import com.codewithshuaib.blog.services.FileService;
import com.codewithshuaib.blog.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable String userId,
                                              @PathVariable String categoryId){

        PostDto cratedPostDto=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(cratedPostDto, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PostResponse> getPostByUser( @PathVariable String userId,
                                                        @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                        @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize){
        PostResponse postResponse=this.postService.getPostByUser(userId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PostResponse> getPostByCategory( @PathVariable String categoryId,
                                                            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
                                                            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize){
        PostResponse postResponse=this.postService.getPostByCategory(categoryId,pageNumber,pageSize);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }


    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value="pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDirection",defaultValue = AppConstants.SORT_DIRECTION,required = false)String sortDirection){
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDirection);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable String postId){
        PostDto postDto=this.postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable String postId){
        PostDto updatedDto=this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedDto, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId){
        this.postService.deletePost(postId);
        return new ResponseEntity<String>("Deleted successfully!", HttpStatus.OK);
    }
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(
            @PathVariable String keywords
    ){
        List<PostDto> result=this.postService.searchPosts(keywords);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@PathVariable String postId,
                                                         @RequestParam("image")MultipartFile image) throws IOException {
        PostDto postDto=this.postService.getPostById(postId);
        String fileName= this.fileService.uploadImage(path,image);
        postDto.setImageName(fileName);
        PostDto updatedPost=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }
    @GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

}

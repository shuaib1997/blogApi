package com.codewithshuaib.blog.controllers;

import com.codewithshuaib.blog.payloads.CommentDto;
import com.codewithshuaib.blog.repositories.CommentRepository;
import com.codewithshuaib.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable String postId){
        CommentDto createdComment=this.commentService.createComment(commentDto,postId);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
                                                    @PathVariable String commentId){
        this.commentService.deleteComment(commentId);
        return new ResponseEntity<>("deleted successfully!", HttpStatus.OK);
    }
}

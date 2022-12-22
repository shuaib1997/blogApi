package com.codewithshuaib.blog.services.impl;

import com.codewithshuaib.blog.entities.Comment;
import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.exceptions.ResourceNotFoundException;
import com.codewithshuaib.blog.payloads.CommentDto;
import com.codewithshuaib.blog.repositories.CommentRepository;
import com.codewithshuaib.blog.repositories.PostRepository;
import com.codewithshuaib.blog.services.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Long postId) {
        Post post=this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","id",postId+""));
       Comment comment= this.modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        Comment savedComment=this.commentRepository.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Long commentId) {
        Comment comment=this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId+""));
        this.commentRepository.delete(comment);
    }
}

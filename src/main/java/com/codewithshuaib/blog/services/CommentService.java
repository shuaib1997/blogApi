package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, String postId);
    void deleteComment(String commentId);
}

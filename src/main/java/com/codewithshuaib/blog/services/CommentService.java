package com.codewithshuaib.blog.services;

import com.codewithshuaib.blog.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Long postId);
    void deleteComment(Long commentId);
}

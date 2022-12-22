package com.codewithshuaib.blog.payloads;


import lombok.Data;

@Data
public class CommentDto {
    private Long commentId;
    private String content;
}

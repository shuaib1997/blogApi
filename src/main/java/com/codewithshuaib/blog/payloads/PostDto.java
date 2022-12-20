package com.codewithshuaib.blog.payloads;

import lombok.Data;

import java.util.*;

@Data
public class PostDto {
    private String postId;
    private String postTitle;
    private String imageName;
    private String content;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

    private List<CommentDto> comments=new ArrayList<>();

}

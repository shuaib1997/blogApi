package com.codewithshuaib.blog.payloads;

import com.codewithshuaib.blog.entities.Category;
import com.codewithshuaib.blog.entities.User;
import lombok.Data;

import java.util.Date;

@Data
public class PostDto {

    private String postTitle;
    private String imageName;
    private String content;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

}

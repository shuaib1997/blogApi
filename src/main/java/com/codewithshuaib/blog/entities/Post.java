package com.codewithshuaib.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "posts")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String postId;
    @Column(name = "post_title",length = 100,nullable = false)
    private String postTitle;
    private String imageName;
    @Column(length = 10000)
    private String content;
    private Date addedDate;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

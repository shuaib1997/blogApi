package com.codewithshuaib.blog.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String commentId;
    private String content;

    @ManyToOne()
    @JoinColumn(name = "post_id")
    private Post post;
}

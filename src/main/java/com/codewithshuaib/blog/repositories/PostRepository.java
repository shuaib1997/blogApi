package com.codewithshuaib.blog.repositories;

import com.codewithshuaib.blog.entities.Category;
import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.entities.User;
import com.codewithshuaib.blog.payloads.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category category,Pageable pageable);
    List<Post> findByPostTitleContaining(String title);
}

package com.codewithshuaib.blog.repositories;

import com.codewithshuaib.blog.entities.Category;
import com.codewithshuaib.blog.entities.Post;
import com.codewithshuaib.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,String> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
}

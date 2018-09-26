package com.wilson404.blog.domain;

import com.wilson404.blog.entity.BlogPostEntity;
import com.wilson404.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Integer> {

    List<BlogPostEntity> findByAuthorIn(UserEntity author);

}

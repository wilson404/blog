package com.wilson404.blog.domain;

import com.wilson404.blog.entity.BlogPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPostEntity, Integer> {
}

package com.wilson404.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepository extends JpaRepository<BlogPost, Integer> {
}

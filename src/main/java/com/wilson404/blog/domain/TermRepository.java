package com.wilson404.blog.domain;

import com.wilson404.blog.entity.TermEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepository extends JpaRepository<TermEntity, Integer> {
    TermEntity findBySlug(String slug);
}

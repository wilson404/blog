package com.wilson404.blog.domain;

import com.wilson404.blog.entity.TermEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "TermEntity")
public interface TermRepository extends JpaRepository<TermEntity, Integer> {
    @Cacheable(key ="'term_'.concat(#p0)")
    TermEntity findBySlug(String slug);

    @CachePut(key = "#p0.slug")
    @Override
    <S extends TermEntity> S save(S s);

    @CacheEvict
    @Override
    void delete(Integer integer);

    @CacheEvict(key = "#p0.slug")
    @Override
    void delete(TermEntity termEntity);
}

package com.wilson404.blog.domain;

import com.wilson404.blog.entity.UserEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
@CacheConfig(cacheNames = "userEntity")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Cacheable()
    UserEntity findByUserLogin(String userLogin);
}

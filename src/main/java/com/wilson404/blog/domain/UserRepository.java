package com.wilson404.blog.domain;

import com.wilson404.blog.entity.UserEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
@CacheConfig(cacheNames = "userEntity")
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Cacheable(key ="'user_'.concat(#p0)")
    UserEntity findByUserLogin(String userLogin);

    @Cacheable(key ="'user_all'")
    @Override
    List<UserEntity> findAll();

    @CachePut(key = "'user_'.concat(#p0.userLogin)")
    @CacheEvict(key = "'user_all'")
    @Override
    <S extends UserEntity> S save(S s);

    @CacheEvict
    @Override
    void delete(Integer integer);

    @CacheEvict(key = "'user_'.concat(#p0.userLogin)")
    @Override
    void delete(UserEntity userEntity);
}

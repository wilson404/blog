package com.wilson404.blog.domain;

import com.wilson404.blog.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findUserByUserLogin(String userLogin);
}

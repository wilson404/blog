package com.wilson404.blog.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByUserLogin(String userLogin);
}

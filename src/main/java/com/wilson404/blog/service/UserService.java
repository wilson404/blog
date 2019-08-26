package com.wilson404.blog.service;

import com.wilson404.blog.common.Role;
import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.entity.UserEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService {
    ServerResponse<UserEntity> login(UserEntity user);
    ServerResponse<UserEntity> register(UserEntity user);
    @PreAuthorize("hasRole(T(com.wilson404.blog.common.Role).ADMIN)")
    ServerResponse<List<UserEntity>> selectAllUser();
}

package com.wilson404.blog.service;

import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.entity.UserEntity;

import java.util.List;

public interface UserService {
    ServerResponse<UserEntity> login(UserEntity user);
    ServerResponse<UserEntity> register(UserEntity user);
    ServerResponse<List<UserEntity>> selectAllUser();
}

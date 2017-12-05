package com.wilson404.blog.service;

import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.domain.User;

import java.util.List;

public interface UserService {
    ServerResponse<User> login(User user);
    ServerResponse<User> register(User user);
    ServerResponse<List<User>> selectAllUser();
}

package com.wilson404.blog.service;

import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.domain.User;

public interface UserService {
    ServerResponse<User> login(User user);
    ServerResponse<User> register(User user);
}

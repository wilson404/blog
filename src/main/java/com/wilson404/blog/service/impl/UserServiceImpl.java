package com.wilson404.blog.service.impl;

import com.wilson404.blog.common.ResponseCode;
import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.entity.UserEntity;
import com.wilson404.blog.domain.UserRepository;
import com.wilson404.blog.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ServerResponse<UserEntity> login(UserEntity user) {
        UserEntity tempUser = userRepository.findUserByUserLogin(user.getUserLogin());
        if (tempUser == null){
            return ServerResponse.createByError(ResponseCode.USER_NOT_FOUND);
        }
        if (tempUser.getPassword().equals(DigestUtils.md5Hex(user.getPassword() + tempUser.getSalt()))) {
            tempUser.setPassword("");
            return ServerResponse.createBySuccess(tempUser);
        } else {
            return ServerResponse.createByError(ResponseCode.PASSWORD_ERROR);
        }
    }

    @Override
    public ServerResponse<UserEntity> register(UserEntity user) {
        UserEntity tempUser = userRepository.findUserByUserLogin(user.getUserLogin());
        if (tempUser != null)
            return ServerResponse.createByErrorMessage("已存在同名用户");
        if (user.getPassword() == null || user.getEmail() == null)
            return ServerResponse.createByError(ResponseCode.ILLEGAL_ARGUMENT);
        String salt = UUID.randomUUID().toString();
        user.setSalt(salt);
        user.setPassword(DigestUtils.md5Hex(user.getPassword() + user.getSalt()));
        tempUser = userRepository.saveAndFlush(user);
        if (tempUser == null) return ServerResponse.createByErrorMessage("新建用户失败");
        return ServerResponse.createBySuccess(tempUser);
    }

    @Override
    public ServerResponse<List<UserEntity>> selectAllUser() {
        return ServerResponse.createBySuccess(userRepository.findAll());
    }
}

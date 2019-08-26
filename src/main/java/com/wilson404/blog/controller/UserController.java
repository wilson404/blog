package com.wilson404.blog.controller;

import com.wilson404.blog.common.ResponseCode;
import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.dto.SessionVO;
import com.wilson404.blog.entity.UserEntity;
import com.wilson404.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    private final SessionVO sessionVO;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, SessionVO sessionVO, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.sessionVO = sessionVO;
        this.passwordEncoder = passwordEncoder;
    }

    @RequestMapping(value = "/hasLogin")
    @ResponseBody
    public ServerResponse<UserEntity> hasLogin() {
        UserEntity userEntity = sessionVO.getUserEntity();
        if (userEntity != null) {
            return ServerResponse.createBySuccess(userEntity);
        }
        return ServerResponse.createByError(ResponseCode.NEED_LOGIN);
    }

    @RequestMapping(value = "/all")
    @ResponseBody
    public ServerResponse<List<UserEntity>> all() {
        return userService.selectAllUser();
    }

    @RequestMapping("/register")
    @ResponseBody
    public ServerResponse<UserEntity> register(@RequestBody UserEntity user) {
        return userService.register(user);
    }
}

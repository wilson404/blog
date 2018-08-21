package com.wilson404.blog.controller;

import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.entity.UserEntity;
import com.wilson404.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public ServerResponse login(UserEntity user, HttpSession session) {
        ServerResponse ret = userService.login(user);
        if (ret.isSuccess()) {
            session.setAttribute("user", user);
        }
        return ret;
    }

    @RequestMapping("/register.do")
    public ServerResponse register(UserEntity user) {
        return userService.register(user);
    }
}

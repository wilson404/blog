package com.wilson404.blog.controller;

import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.domain.User;
import com.wilson404.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/login.do")
    public ServerResponse login(String userlogin, String password, HttpSession session) {
        User user = new User();
        user.setUserLogin(userlogin);
        user.setPassword(password);
        ServerResponse ret = userService.login(user);
        if (ret.isSuccess()) {
            session.setAttribute("user", user);
        }
        return ret;
    }
}

package com.wilson404.blog.controller;

import com.wilson404.blog.common.ResponseCode;
import com.wilson404.blog.common.ServerResponse;
import com.wilson404.blog.dto.SessionVO;
import com.wilson404.blog.entity.UserEntity;
import com.wilson404.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    private final SessionVO sessionVO;

    @Autowired
    public UserController(UserService userService,SessionVO sessionVO) {
        this.userService = userService;
        this.sessionVO = sessionVO;
    }

//    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
//    @ResponseBody
//    public ServerResponse login(@RequestBody  UserEntity user) {
//        ServerResponse<UserEntity> ret = userService.login(user);
//        if (ret.isSuccess()) {
//            sessionVO.setUserEntity(ret.getData());
//        }
//        return ret;
//    }

    @RequestMapping(value = "/hasLogin")
    @ResponseBody
    public ServerResponse<UserEntity> hasLogin() {
        UserEntity userEntity = sessionVO.getUserEntity();
        if (userEntity !=null){
            return ServerResponse.createBySuccess(userEntity);
        }
        return ServerResponse.createByError(ResponseCode.NEED_LOGIN);
    }

    @RequestMapping("/register")
    @ResponseBody
    public ServerResponse<UserEntity> register(@RequestBody UserEntity user) {
        return userService.register(user);
    }
}

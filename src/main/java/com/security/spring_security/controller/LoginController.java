package com.security.spring_security.controller;

import com.security.spring_security.common.ResponseResult;
import com.security.spring_security.entity.User;
import com.security.spring_security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 原野
 * @DATE 2023/9/4 15:29
 * @Description:
 * @Version 1.0
 */
@RestController
public class LoginController {


    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

}

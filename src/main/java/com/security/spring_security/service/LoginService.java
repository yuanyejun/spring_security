package com.security.spring_security.service;

import com.security.spring_security.common.ResponseResult;
import com.security.spring_security.entity.User;

/**
 * @Author 原野
 * @DATE 2023/9/4 15:29
 * @Description:
 * @Version 1.0
 */
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();
}

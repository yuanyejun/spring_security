package com.security.spring_security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.security.spring_security.common.LoginUser;
import com.security.spring_security.common.RedisCache;
import com.security.spring_security.common.ResponseResult;
import com.security.spring_security.entity.User;
import com.security.spring_security.mapper.UserMapper;
import com.security.spring_security.service.LoginService;
import com.security.spring_security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Objects;

/**
 * @Author 原野
 * @DATE 2023/9/4 15:30
 * @Description:
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService{

    @Resource
    private RedisCache redisCache;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResponseResult login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword());
      //   authenticationManager 进行用户验证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //如果验证没通过,给出对应的提示
        if (Objects.isNull(authenticate)){
            throw  new RuntimeException("登陆失败");
        }
        //如果认证通过了 使用userid生成一个jwt,jwt存入ResponseResult返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        HashMap<String, String> map = new HashMap<>();
        map.put("token",jwt);
        //把完整的用户信息存入redis,userid作为key
        redisCache.setCacheObject("login:" + userId,loginUser);

        return new ResponseResult(200,"登陆成功",map);
    }

    @Override
    public ResponseResult logout() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("login:" + userId);

        return new ResponseResult(200,"退出成功");
    }
}

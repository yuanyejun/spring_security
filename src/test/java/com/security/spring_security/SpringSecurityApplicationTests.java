package com.security.spring_security;

import com.security.spring_security.mapper.MenuMapper;
import com.security.spring_security.mapper.UserMapper;
import com.security.spring_security.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringSecurityApplicationTests {

    @Resource
    private UserMapper userMapper;

    @Test
    void contextLoads() {

//        System.out.println(userMapper.selectCount(null));

        try {
            Claims claims = JwtUtil.parseJWT("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJleHAiOjE2OTQ0MDMwMzAsInVzZXJuYW1lIjoiemhhbmdzYW4ifQ.LxBMCHc74-TwwTBu1MjZB1aY5I48iZ6drCQHQTS3ogQ");
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void contextLoads2() {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("123456"));

    }

    @Resource
    private MenuMapper menuMapper;

    @Test
    void contextLoads3() {

        List<String> strings = menuMapper.selectPermsByUserId(2l);
        System.out.println(strings);

    }

}

package com.security.spring_security.handle;

import com.alibaba.fastjson.JSON;
import com.security.spring_security.common.ResponseResult;
import com.security.spring_security.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 原野
 * @DATE 2023/9/8 10:48
 * @Description:
 * @Version 1.0
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ResponseResult result = new ResponseResult(HttpStatus.UNAUTHORIZED.value(), "用户认证未通过请重新登陆");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(httpServletResponse,json);

    }
}

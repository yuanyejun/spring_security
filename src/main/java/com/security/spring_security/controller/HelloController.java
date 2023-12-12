package com.security.spring_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 原野
 * @DATE 2023/8/31 14:11
 * @Description:
 * @Version 1.0
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('system:dept:list')")
    public String hello(){
        return "hello test";
    }

}

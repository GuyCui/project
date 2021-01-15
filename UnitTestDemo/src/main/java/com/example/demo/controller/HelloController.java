package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: HelloController
 * Date: 2021/1/15 10:50 下午
 * Description: 用于测试的控制器
 * @Author：guycui
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(String name){
        return "hello "+name;
    }
}

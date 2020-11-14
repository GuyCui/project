package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guycui
 */
@RestController
public class SpringBootController {
    @RequestMapping("/hello")
    public String hello(){
        return "Hello,Spring Boot!";
    }

    @Autowired
    private GetPersonInfoProperties getPersonInfoProperties;

    @RequestMapping("/getInfo")
    public String getInfo(){
       return "姓名："+getPersonInfoProperties.getName() + "，年龄：" +getPersonInfoProperties.getAge();
    }
}

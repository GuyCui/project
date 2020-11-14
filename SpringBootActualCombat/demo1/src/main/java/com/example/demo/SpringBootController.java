package com.example.demo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guycui
 */
@RestController
@RunWith(SpringRunner.class)
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

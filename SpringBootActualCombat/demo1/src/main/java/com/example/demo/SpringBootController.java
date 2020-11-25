package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guycui
 */
@RestController
public class SpringBootController {
  @Autowired
  private GetPersonInfoProperties getPersonInfoProperties;
  @Autowired
  private CoExample coExample;

  @RequestMapping("/hello")
  public String hello() {
    return "Hello,Spring Boot!";
  }

  @RequestMapping("/getInfo")
  public String getInfo() {
    return "姓名：" + getPersonInfoProperties.getName() + "，年龄：" + getPersonInfoProperties.getAge();
  }

  @RequestMapping("/coExample")
  public String getExample() {
    return "姓名：" + coExample.getName() + ",年龄：" + coExample.getAge() + ",地区:" + coExample.getAddress();

  }
}

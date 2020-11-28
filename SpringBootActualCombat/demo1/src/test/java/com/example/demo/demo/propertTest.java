package com.example.demo.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;

/** @author guycui */
// 用于测试的注解，可指定入口类或测试环境等。
@SpringBootTest
// 在 Spring 测试环境中进行测试
@RunWith(SpringRunner.class)
public class propertTest {

  /** 获取配置文件中 age */
  @Value("${age}")
  private int age;

  /**
   * 获取配置文件中的 name
   */
  @Value("${name}")
  private String name;

  /**
   * 该注解表示一个测试方法
   */
  @Test
  public void getAge(){
    MockServletContext logger = new MockServletContext();
    logger.log(String.valueOf(age));
  }

  /**
   * 该注解表示一个测试方法
   */
  @Test
  public void getName(){
    MockServletContext logger = new MockServletContext();
    logger.log(name);
  }

  @Autowired
  private GetPersonInfoProperties getPersonInfoProperties;

  @Test
  public void getPersonInfoProperties(){
    System.out.println(getPersonInfoProperties.getName()+"===="+getPersonInfoProperties.getAge());
  }
}

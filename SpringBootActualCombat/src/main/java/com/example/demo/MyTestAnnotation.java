package com.example.demo;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * FileName: MyTestAnnotation
 * Date: 2021/1/2 12:20 下午
 * @Target:注解标注作用范围
 * @Retention：注解生命周期
 * @Description: 自定义注解类
 * @Documented：将注解信息添加到 Java 文档中
 * @Author：guycui
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyTestAnnotation {
    String value();
}

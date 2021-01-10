package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * FileName: TestAnnotationAspect
 * Date: 2021/1/2 12:25 下午
 * Description: 实现业务逻辑
 * @Author：guycui
 */
@Aspect
@Component
public class TestAnnotationAspect {
    @Pointcut("@annotation(com.example.demo.MyTestAnnotation)")
    public void myAnnotationPointCut(){
    }
    @Before("myAnnotationPointCut()")
    public void before(JoinPoint joinPoint){
        MethodSignature sign = (MethodSignature) joinPoint.getSignature();
        Method method = sign.getMethod();
        MyTestAnnotation annotation = method.getAnnotation(MyTestAnnotation.class);
        // 获取注解参数
        System.out.println("TestAnnotation 参数：" + annotation.value());
    }

    @MyTestAnnotation("测试 Annotation 参数")
    public void testAnnotation(){}
}

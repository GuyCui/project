package com.example.webFlux.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * FileName: AopLog
 * @Author：guycui
 * Date: 2020/11/27 下午9:35
 * Description: Aop 日志注解
 */
@Aspect
@Component
public class AopLog {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// 线程局部变量，用于解决多线程中相同变量的访问冲突问题
	ThreadLocal<Long> startTime = new ThreadLocal<>();

	/**
	 * 定义切点
 	 */
	@Pointcut("execution(public * com.example..*.*(..))")
	public void aopWebLog(){}

	@Before("aopWebLog()")
	public void doBefore(JoinPoint joinPoint) {
		startTime.set(System.currentTimeMillis());

		// 接收到请求，记录请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();

		// 记录下请求内容
		logger.info("URL:" + request.getRequestURI());
		logger.info("HTTP方法：" + request.getMethod());
		logger.info("IP地址" + request.getRemoteAddr());
		logger.info("类的方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("参数：" + request.getQueryString());
	}
}

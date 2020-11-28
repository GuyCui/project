package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
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
		logger.info("URL:" + request.getRequestURL().toString());
		logger.info("HTTP方法：" + request.getMethod());
		logger.info("IP地址" + request.getRemoteAddr());
		logger.info("类的方法：" + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		logger.info("参数：" + request.getQueryString());
	}

	@AfterReturning(pointcut = "aopWebLog()",returning = "retObject")
	public void doAfterReturning(Object retObject) throws Throwable{
		// 处理完请求，返回内容
		logger.info("答应值："+ retObject);
		logger.info("费事："+(System.currentTimeMillis() - startTime.get()));
	}

	/**
	 * 方法抛出异常退出时执行的通知
	 * @param joinPoint
	 * @param ex
	 */
	@AfterThrowing(pointcut = "aopWebLog()",throwing = "ex")
	public void addAfterThrowingLogger(JoinPoint joinPoint,Exception ex){
		logger.error("执行："+ "异常",ex);
	}
}

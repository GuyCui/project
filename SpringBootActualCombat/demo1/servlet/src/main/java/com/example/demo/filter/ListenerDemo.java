package com.example.demo.filter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * FileName: listenerDemo
 * Date: 2020/12/10 9:06 下午
 * Description: 实现监听器
 * @Author：guycui
 */
@WebListener
public class ListenerDemo implements ServletContextListener{
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContex初始化");
		System.out.println(servletContextEvent.getServletContext().getServerInfo());
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContex销毁");
	}
}

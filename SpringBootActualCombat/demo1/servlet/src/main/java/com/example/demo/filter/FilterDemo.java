package com.example.demo.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * FileName: FiterDemo
 * Date: 2020/12/10 8:11 下午
 * Description: 拦截器
 * @Author：guycui
 */
@WebFilter(urlPatterns = "/*")
public class FilterDemo implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		System.out.println("拦截器");
		chain.doFilter(request,response);
	}

	@Override
	public void destroy() {
	}
}

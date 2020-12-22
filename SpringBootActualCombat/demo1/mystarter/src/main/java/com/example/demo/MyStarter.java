package com.example.demo;

/**
 * FileName: MyStarter
 * Date: 2020/12/22 10:00 下午
 * Description: 核心服务类
 * @Author：guycui
 */
public class MyStarter {
	private MyStarterProperties myStarterProperties;
	public MyStarter(){}
	public MyStarter(MyStarterProperties myStarterProperties){
		this.myStarterProperties = myStarterProperties;
	}
	public String print(){
		System.out.println("参数：" + myStarterProperties.getParameter());
		String s = myStarterProperties.getParameter();
		return s;
	}
}

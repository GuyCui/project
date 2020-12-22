package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName: MyStarterServiceAutoConfiguration
 * Date: 2020/12/22 10:05 下午
 * Description: 自动配置类
 * @ConditionalOnClass: 在类路径 classpath 下有指定的类的情况下进行自动配置
 * @ConditionalOnProperty: 属性matchIfMissing = true 进行自动配置
 * @Author：guycui
 */
@Configuration
@EnableConfigurationProperties(MyStarterProperties.class)
@ConditionalOnClass(MyStarter.class)
@ConditionalOnProperty(prefix = "spring.mystarter",value = "enabled",matchIfMissing = true)
public class MyStarterServiceAutoConfiguration {
	@Autowired
	private MyStarterProperties myStarterProperties;
	@Bean
	@ConditionalOnMissingBean(MyStarter.class)
	public MyStarter MyStarterService(){
		MyStarter myStarter = new MyStarter();
		return myStarter;
	}
}

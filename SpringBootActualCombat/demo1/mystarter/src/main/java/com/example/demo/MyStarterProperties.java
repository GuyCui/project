package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FileName: MyStarterProperties
 * Date: 2020/12/22 9:54 下午
 * Description: 自定义 Properties 类
 * @Author：guycui
 */
@ConfigurationProperties(prefix = "spring.mystarter")
@Data
public class MyStarterProperties {
// 参数
	private String parameter;
}

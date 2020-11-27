package com.example.webFlux.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * FileName：User
 * @Author：guycui
 * Date：2020/11/27 下午7:44
 * Description：实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Long id;
	private String name;
	private int age;
}

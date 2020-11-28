package com.example.demo.demo.validator;

import com.example.demo.demo.annotation.MyCustomConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author guycui
 */
public class MyCustomConstraintValidator implements ConstraintValidator<MyCustomConstraint,String> {
	/**
	 * 初始化验证消息
	 * @param constraintAnnotation
	 */
	@Override
	public void initialize(MyCustomConstraint constraintAnnotation) {
		// 启动时执行
	}

	/**
	 * 执行验证的方法
	 * @param value
	 * @param context
	 * @return
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if ("北京".equals(value) || "上海".equals(value)) {
			return true;
		}
		return false;
	}
}

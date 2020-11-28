package com.example.demo.demo.annotation;

import com.example.demo.demo.validator.MyCustomConstraintValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author guycui
 */
// 限定使用范围
@Target({ElementType.FIELD})
// 表明注解的生命周期，它在代码运行时可以通过反射获取到注解
@Retention(RetentionPolicy.RUNTIME)
// validateBy 字段指定该注解的校验逻辑
@Constraint(validatedBy= MyCustomConstraintValidator.class)
public @interface MyCustomConstraint {
	/**
	 * @Description: 错误提示
	 */
	String message() default "请输入中国政治或经济中心的城市名称";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default {};
}

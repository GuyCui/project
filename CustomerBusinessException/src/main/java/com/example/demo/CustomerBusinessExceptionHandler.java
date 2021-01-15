package com.example.demo;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: CustomerBusinessExceptionHandler
 * Date: 2021/1/13 9:05 下午
 * Description: 自定义全局捕获异常
 * @Author：guycui
 */
public class CustomerBusinessExceptionHandler {
    /**
     * 自定义业务处理业务异常类
     */
    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> businessExceptionHandler(BusinessException e){
        Map<String, Object> map = new HashMap<>(2);
        map.put("code",e.getCode());
        map.put("message",e.getMessage());
        return map;
    }

}

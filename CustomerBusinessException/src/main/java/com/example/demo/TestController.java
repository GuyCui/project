package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: TestController
 * Date: 2021/1/13 9:16 下午
 * Description: 测试自定义异常类
 * @Author：guycui
 */
@RestController
public class TestController {
    @RequestMapping("/BusinessException")
    public String testResStatusExceptionResolver(@RequestParam("i")int i){
        if (i == 0){
            throw new BusinessException(600, "自定义业务错误");
        }
        return "success";
    }
}

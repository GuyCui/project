package com.example.demo;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName: TestErrorController
 * Date: 2021/1/12 10:48 下午
 * Description: 自定义错误的处理控制器
 * @Author：guycui
 */
@RestController
@RequestMapping("/error")
public class TestErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return null;
    }
    @RequestMapping
    public Map<String, Object> handleError(){
        // 用 Map 容器返回信息
        Map<String, Object> map = new HashMap<>();
        map.put("code",404);
        map.put("msg","不存在");
        return map;
    }

    @RequestMapping("/ok")
    @ResponseBody
    public Map<String, Object> noError(){
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","正常，这是测试页面");
        return map;
    }
}

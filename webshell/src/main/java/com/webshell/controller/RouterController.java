package com.webshell.controller;

import com.webshell.util.ExtResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: RouterController
 * Date: 2021/1/19 9:23 下午
 * Description: 路由控制层
 * @Author：guycui
 */
@RestController
@AllArgsConstructor
public class RouterController {
    @PostMapping("/webShell")
    public Object webShellPage(@RequestParam String userName, @RequestParam String ip, @RequestParam String port,
                               @RequestParam String Password) {
        System.out.println(userName);
        String sessionKey = "webssh";
        return ExtResult.success(sessionKey);
        // return "webssh";
    }
}

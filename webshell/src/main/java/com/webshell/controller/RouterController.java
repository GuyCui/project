package com.webshell.controller;

import com.webshell.entity.WebShellData;
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
    public Object webShellPage(@RequestParam String userName, @RequestParam String ip, @RequestParam Integer port,
                               @RequestParam String password) {
        WebShellData webShell = new WebShellData();
        webShell.setIp(ip);
        webShell.setUserName(userName);
        webShell.setPort(port);
        webShell.setPassword(password);

        return ExtResult.success(webShell,"登录成功");
        // return "webssh";
    }
}

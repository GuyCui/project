package com.webshell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FileName: RouterController
 * Date: 2021/1/19 9:23 下午
 * Description: 路由控制层
 * @Author：guycui
 */
@Controller
public class RouterController {
    @RequestMapping("/webShell")
    public String webShellPage() {
        return "webssh";
    }
}

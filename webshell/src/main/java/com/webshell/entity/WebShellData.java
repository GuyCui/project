package com.webshell.entity;

import lombok.Data;

/**
 * FileName: WebShellData
 * Date: 2021/1/19 9:17 下午
 * Description: 连接数据实体类
 * @Author：guycui
 */
@Data
public class WebShellData {
    /**
     * 操作
     */
    private String operate;

    /**
     * IP 地址
     */
    private String ip;

    /**
     * 端口号 默认 22
     */
    private Integer port = 22;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 连接方式
     */
    private String command;

}

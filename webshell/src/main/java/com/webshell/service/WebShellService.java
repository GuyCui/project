package com.webshell.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

/**
 * FileName: WebShellService
 * Date: 2021/1/19 9:30 下午
 * Description: 业务接口
 * @Author：guycui
 */
public interface WebShellService {
    /**
     * 初始化连接信息
     * @param socketSession 网络会话
     */
    void initConnection(WebSocketSession socketSession);

    /**
     * 处理发送的命令数据
     * @param buffer 缓冲
     * @param socketSession 网络会话
     */
    void recvHandle(String buffer, WebSocketSession socketSession);

    /**
     * 数据回传前台
     * @param socketSession 网络会话
     * @param buffer 缓冲
     * @exception IOException IO异常
     */
    void sendMessage(WebSocketSession socketSession, byte[] buffer) throws IOException;

    /**
     * 关闭连接
     * @param socketSession 网络会话
     */
    void close(WebSocketSession socketSession);

}

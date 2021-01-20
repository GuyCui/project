package com.webshell.config;

import com.webshell.interceptor.WebSocketInterceptor;
import com.webshell.websocket.WebShellSocketHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * FileName: WebShellConfig
 * Date: 2021/1/19 10:19 下午
 * Description: websocket 配置
 * @Author：guycui
 */
@Configuration
@EnableWebSocket
@AllArgsConstructor
public class WebShellConfig implements WebSocketConfigurer {
    WebShellSocketHandler webShellSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        /**
         * socket通道
         * 指定处理器和路径
         */
        webSocketHandlerRegistry.addHandler(webShellSocketHandler, "/webssh")
                .addInterceptors(new WebSocketInterceptor())
                .setAllowedOrigins("*");
    }
}

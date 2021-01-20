package com.webshell.websocket;


import com.webshell.constant.ConstantPool;
import com.webshell.service.WebShellService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;


/**
 * @Description: WebSSH的WebSocket处理器
 * @Author GuyCui
 * @Date: 2020/3/8
 */
@Component
@AllArgsConstructor
public class WebShellSocketHandler implements WebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(WebShellSocketHandler.class);

    private WebShellService webShellService;

    /**
     * @Description: 用户连接上WebSocket的回调
     * @Param: [webSocketSession]
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        logger.info("用户:{},连接WebSSH", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //调用初始化连接
        webShellService.initConnection(webSocketSession);
    }

    /**
     * @Description: 收到消息的回调
     * @Param: [webSocketSession, webSocketMessage]
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        if (webSocketMessage instanceof TextMessage) {
            logger.info("用户:{},发送命令:{}", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY),
                    webSocketMessage);
            //调用service接收消息
            webShellService.recvHandle(((TextMessage) webSocketMessage).getPayload(), webSocketSession);
        } else if (webSocketMessage instanceof BinaryMessage) {

        } else if (webSocketMessage instanceof PongMessage) {

        } else {
            logger.info("意外的 WebSocket 消息类型: {}", webSocketMessage);
        }
    }

    /**
     * @Description: 出现错误的回调
     * @Param: [webSocketSession, throwable]
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        logger.error("数据传输错误");
    }

    /**
     * @Description: 连接关闭的回调
     * @Param: [webSocketSession, closeStatus]
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        logger.info("用户:{}断开webShell连接", webSocketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //调用service关闭连接
        webShellService.close(webSocketSession);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

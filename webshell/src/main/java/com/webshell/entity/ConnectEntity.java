package com.webshell.entity;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

/**
 * FileName: ConnectEntity
 * Date: 2021/1/19 9:13 下午
 * Description: ssh连接实体类
 * @Author：guycui
 */
@Data
public class ConnectEntity {

    private WebSocketSession webSocketSession;

    private JSch jSch;

    private Channel channel;
}

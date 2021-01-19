package com.webshell.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.webshell.constant.ConstantPool;
import com.webshell.entity.ConnectEntity;
import com.webshell.entity.WebShellData;
import com.webshell.service.WebShellService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FileName: WebShellServiceImpl
 * Date: 2021/1/19 9:43 下午
 * Description: 业务接口实现类
 * @Author：guycui
 */
@Service
public class WebShellServiceImpl implements WebShellService {
    /**
     * 存放ssh连接信息的map
     */
    private static Map<String, Object> sshMap = new ConcurrentHashMap<>();

    private final Logger logger = LoggerFactory.getLogger(WebShellServiceImpl.class);

    /**
     * 线程池
     */
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void initConnection(WebSocketSession socketSession) {
        JSch jSch = new JSch();
        ConnectEntity sshConnectInfo = new ConnectEntity();
        sshConnectInfo.setJSch(jSch);
        sshConnectInfo.setWebSocketSession(socketSession);
        String uuid = String.valueOf(socketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        //将这个ssh连接信息放入map中
        sshMap.put(uuid, sshConnectInfo);
    }

    @Override
    public void recvHandle(String buffer, WebSocketSession socketSession) {
        ObjectMapper objectMapper = new ObjectMapper();
        WebShellData webShellData;
        try {
            webShellData = objectMapper.readValue(buffer, WebShellData.class);
        } catch (IOException e) {
            logger.error("Json转换异常");
            logger.error(ConstantPool.ABNORMITY, e.getMessage());
            return;
        }
        String userId = String.valueOf(socketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        if (ConstantPool.WEB_SHELL_OPERATE_CONNECT.equals(webShellData.getOperate())) {
            //找到刚才存储的ssh连接对象
            ConnectEntity connectEntity = (ConnectEntity) sshMap.get(userId);
            //启动线程异步处理
            executorService.execute(() -> {
                try {
                    connectToShell(connectEntity, webShellData, socketSession);
                } catch (JSchException | IOException e) {
                    logger.error("webShell连接异常");
                    logger.error(ConstantPool.ABNORMITY, e.getMessage());
                    close(socketSession);
                }
            });
        } else if (ConstantPool.WEB_SHELL_OPERATE_COMMAND.equals(webShellData.getOperate())) {
            String command = webShellData.getCommand();
            ConnectEntity connectEntity = (ConnectEntity) sshMap.get(userId);
            if (connectEntity != null) {
                try {
                    transToShell(connectEntity.getChannel(), command);
                } catch (IOException e) {
                    logger.error("webShell连接异常");
                    logger.error(ConstantPool.ABNORMITY, e.getMessage());
                    close(socketSession);
                }
            }
        } else {
            logger.error("不支持的操作");
            close(socketSession);
        }
    }

    @Override
    public void sendMessage(WebSocketSession socketSession, byte[] buffer) throws IOException {
        socketSession.sendMessage(new TextMessage(buffer));
    }

    @Override
    public void close(WebSocketSession socketSession) {
        String userId = String.valueOf(socketSession.getAttributes().get(ConstantPool.USER_UUID_KEY));
        ConnectEntity connectEntity = (ConnectEntity) sshMap.get(userId);
        if (connectEntity != null) {
            //断开连接
            if (connectEntity.getChannel() != null) {
                connectEntity.getChannel().disconnect();
            }
            //map中移除
            sshMap.remove(userId);
        }
    }

    /**
     * 使用jsch连接终端
     * @Param: [cloudSSH, webSSHData, webSocketSession]
     */
    private void connectToShell(ConnectEntity connectEntity, WebShellData webShellData,
                                WebSocketSession webSocketSession) throws JSchException, IOException {
        Session session;
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //获取jsch的会话
        session = connectEntity.getJSch().getSession(webShellData.getUserName(), webShellData.getIp(),
                webShellData.getPort());
        session.setConfig(config);
        //设置密码
        session.setPassword(webShellData.getPassword());
        //连接  超时时间30s
        session.connect(30000);

        //开启shell通道
        Channel channel = session.openChannel("shell");

        //通道连接 超时时间3s
        channel.connect(3000);

        //设置channel
        connectEntity.setChannel(channel);

        //转发消息
        transToShell(channel, "\r");

        //读取终端返回的信息流
        try (InputStream inputStream = channel.getInputStream()) {
            //循环读取
            byte[] buffer = new byte[1024];
            int i = 0;
            //如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = inputStream.read(buffer)) != -1) {
                sendMessage(webSocketSession, Arrays.copyOfRange(buffer, 0, i));
            }

        } finally {
            //断开连接后关闭会话
            session.disconnect();
            channel.disconnect();
        }

    }

    /**
     * 将消息转发到终端
     * @Param: [channel, data]
     */
    private void transToShell(Channel channel, String command) throws IOException {
        if (channel != null) {
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
        }
    }
}

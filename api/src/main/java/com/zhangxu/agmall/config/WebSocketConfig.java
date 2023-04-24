package com.zhangxu.agmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author zhangxu
 * @create 2023-04-22
 */
@Configuration
public class WebSocketConfig {
    /**
     *
     * 将服务器终端接口交给spring
     * @return
     */
    @Bean
    public ServerEndpointExporter getServerEndpointExporter(){
        return new ServerEndpointExporter();
    }
}

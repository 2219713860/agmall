package com.zhangxu.agmall.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangxu
 * @create 2023-04-22
 */
@Component
@ServerEndpoint("/webSocket/{oid}")
public class WebSocketServer {
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    @OnOpen
    public void open(@PathParam("oid") String oid, Session session) {
        sessionMap.put(oid, session);
            System.out.println("长连接建立完成=============================");
    }

    @OnClose
    public void close(@PathParam("oid") String oid) {
            System.out.println("断开成功=====");
        sessionMap.remove(oid);
    }


    public static void sendMsg(String oid, String msg) {
        Session session = sessionMap.get(oid);
        RemoteEndpoint.Basic basicRemote = session.getBasicRemote();
        try {
            basicRemote.sendText(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

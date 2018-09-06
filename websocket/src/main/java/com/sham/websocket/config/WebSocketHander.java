package com.sham.websocket.config;

import com.sham.common.utils.DateUtil;
import com.sham.common.utils.LoggerUtils;
import com.sham.websocket.dto.SessionContain;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketHander implements WebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        new SessionContain(webSocketSession,webSocketSession.getId(),DateUtil.getCurrTime());
        LoggerUtils.info("新连接加入.. 当前人数:"+SessionContain.containMap.size());
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        SessionContain.delSession(webSocketSession);
        LoggerUtils.info("有人断开连接.. 当前人数:"+SessionContain.containMap.size());
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

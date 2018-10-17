package com.sham.websocket.config;

import com.alibaba.fastjson.JSON;
import com.sham.common.dto.WebSocketData;
import com.sham.common.utils.DateUtil;
import com.sham.common.utils.LoggerUtils;
import com.sham.websocket.dto.SessionContain;
import com.sham.websocket.service.WebSocketService;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

public class WebSocketHander implements WebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        new SessionContain(session, session.getId(), DateUtil.getCurrTime());
        LoggerUtils.info("新连接加入.. 当前人数:" + SessionContain.containMap.size());
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> webSocketMessage) throws Exception {
        SessionContain contain = SessionContain.getSessionContain(session);
        if (contain != null) {
            String message = webSocketMessage.getPayload().toString();
            WebSocketData data = JSON.parseObject(message, WebSocketData.class);
            data.setSessionId(session.getId());
            WebSocketService.dispenser(data);

        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        SessionContain.delSession(webSocketSession);
        LoggerUtils.info("有人断开连接.. 当前人数:" + SessionContain.containMap.size());
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

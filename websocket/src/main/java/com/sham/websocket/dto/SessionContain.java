package com.sham.websocket.dto;

import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

public class SessionContain {
    public static Map<String,SessionContain> containMap=new HashMap<>();
    private WebSocketSession session;
    private String seessionId;
    private Integer userId;
    private Long connectTime;

    public static Map<String, SessionContain> getContainMap() {
        return containMap;
    }

    public static void setContainMap(Map<String, SessionContain> containMap) {
        SessionContain.containMap = containMap;
    }

    public static SessionContain getSessionContain(WebSocketSession session){
        return containMap.get(session.getId());
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public String getSeessionId() {
        return seessionId;
    }

    public void setSeessionId(String seessionId) {
        this.seessionId = seessionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getConnectTime() {
        return connectTime;
    }

    public void setConnectTime(Long connectTime) {
        this.connectTime = connectTime;
    }

    public SessionContain(WebSocketSession session, String seessionId, Long connectTime) {
        this.session = session;
        this.seessionId = seessionId;
        this.connectTime = connectTime;
        this.putSession(this);
    }

    public synchronized static void delSession(WebSocketSession webSocketSession) {
        containMap.remove(webSocketSession.getId());
    }

    public synchronized static void putSession(SessionContain sessionContain){
        containMap.put(sessionContain.getSeessionId(),sessionContain);
    }
}

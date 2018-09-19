package com.sham.common.dto;

public class WebSocketData {
    private String sessionId;
    private Object object;
    private Integer userId;
    private Integer toUserId;

    public WebSocketData(Object object, Integer userId, Integer toUserId) {
        this.object = object;
        this.userId = userId;
        this.toUserId = toUserId;
    }

    public WebSocketData(Object object, Integer toUserId) {
        this.object = object;
        this.toUserId = toUserId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }
}

package com.sham.common.dto;

public class WebSocketData {
    private String sessionId;
    private Object body;
    private Integer userId;
    private Integer toUserId;
    private String msgId;
    private String formId;
    public WebSocketData() {
    }

    public WebSocketData(String sessionId, Object body) {
        this.sessionId = sessionId;
        this.body = body;
    }

    public WebSocketData(String sessionId, String formId, Object body) {
        this.sessionId = sessionId;
        this.body = body;
        this.formId = formId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
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

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getFormId() {
        return formId;
    }

    public void setFormId(String formId) {
        this.formId = formId;
    }
}

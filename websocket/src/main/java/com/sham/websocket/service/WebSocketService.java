package com.sham.websocket.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sham.common.core.IConfig;
import com.sham.common.dto.AjaxResult;
import com.sham.common.dto.WebSocketData;
import com.sham.common.utils.LoggerUtils;
import com.sham.websocket.dto.SessionContain;
import org.springframework.web.socket.TextMessage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class WebSocketService {

    private static Map<String, Map<String, String>> submap = new HashMap<>();

    public static void dispenser(WebSocketData data) {
        if (IConfig.getConfig("protocol.subscribe").equals(data.getMsgId())) {
            addsubscribe(data);
        } else if (IConfig.getConfig("protocol.delsubscribe").equals(data.getMsgId())) {
            removeSubscribe(data);
        }
    }

    public static AjaxResult sendMsg(WebSocketData data) {
        if (data == null) {
            return null;
        }
        try {
            TextMessage textMessage = new TextMessage(JSON.toJSONBytes(data));
            Map<String, SessionContain> sessionContainMap = SessionContain.getContainMap();
            for (Iterator<String> iterator = sessionContainMap.keySet().iterator(); iterator.hasNext(); ) {
                String sessionId = iterator.next();
                if (sessionId.equals(data.getSessionId())) {
                    sessionContainMap.get(sessionId).getSession().sendMessage(textMessage);
                }
            }

        } catch (Exception e) {
            LoggerUtils.error(" 错误啦");
        }
        return new AjaxResult("发送成功");
    }

    public static void addsubscribe(WebSocketData data) {
        JSONObject body = ((JSONObject) data.getBody());
        String protocol = body.getString("protocol");
        submap.put(protocol, new HashMap<>());
        if (!submap.get(protocol).containsKey(data.getSessionId())) {
            String formId = (String) ((JSONObject) data.getBody()).get("formId");
            submap.get(protocol).put(data.getSessionId(), formId);
        }

    }

    public static void removeSubscribe(WebSocketData data) {

        String protocol = (String) data.getBody();
        if (submap.containsKey(protocol)) {
            submap.get(protocol).remove(data.getSessionId());
        }
    }

    public static Map<String, String> getsubscribe(String protocol) {
        return submap.containsKey(protocol) ? submap.get(protocol) : null;
    }

}

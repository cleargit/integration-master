package com.sham.websocket.service;

import com.alibaba.fastjson.JSON;
import com.sham.common.dto.AjaxResult;
import com.sham.common.dto.WebSocketData;
import com.sham.common.utils.LoggerUtils;
import com.sham.websocket.dto.SessionContain;
import org.springframework.web.socket.TextMessage;


public class WebSocketService {

    public static AjaxResult sendMsg(WebSocketData data){
        try {
            Integer userId=data.getToUserId();
            SessionContain sessionContain=SessionContain.getContainMap().get(String.valueOf(userId));
            TextMessage textMessage=new TextMessage(JSON.toJSONBytes(data.getObject()));
            sessionContain.getSession().sendMessage(textMessage);
        }catch (Exception e){
            LoggerUtils.error(" 错误啦");
        }
        return new AjaxResult("发送成功");
    }
}

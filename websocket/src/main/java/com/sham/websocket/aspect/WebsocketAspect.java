package com.sham.websocket.aspect;

import com.sham.common.dto.WebSocketData;
import com.sham.common.utils.LoggerUtils;
import com.sham.websocket.dto.SessionContain;
import com.sham.websocket.service.WebSocketService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WebsocketAspect {

    @Pointcut("@annotation(com.sham.common.annotation.WsSend)")
    public void pointcut(){
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint joinPoint){
        Object args[]=joinPoint.getArgs();
        if (args[0] instanceof WebSocketData){
            WebSocketData data= (WebSocketData) args[0];
            WebSocketService.sendMsg(data);
        }else {
            LoggerUtils.error("到这了 说明代码已死");
        }
    }
}

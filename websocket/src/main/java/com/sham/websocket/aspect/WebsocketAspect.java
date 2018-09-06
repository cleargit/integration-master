package com.sham.websocket.aspect;

import com.sham.common.dto.WebSocketData;
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

        }
    }
}

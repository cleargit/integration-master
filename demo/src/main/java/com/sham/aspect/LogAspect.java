package com.sham.aspect;

import com.sham.common.annotation.ControllerLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Pointcut("@annotation(controllerLog)")
    public void pointcut(ControllerLog controllerLog){
    }

    @Before("pointcut(controllerLog)")
    public void setLog(JoinPoint joinPoint,ControllerLog controllerLog){
        System.out.println(controllerLog.value());
    }
}

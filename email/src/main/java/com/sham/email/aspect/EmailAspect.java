package com.sham.email.aspect;

import com.sham.common.dto.EmailData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmailAspect {

    @Pointcut("@annotation(com.sham.common.annotation.EmailSend)")
    public void pointcut(){}

    @Around("pointcut()")
    public void aroud(ProceedingJoinPoint joinPoint){
        Object[] args=joinPoint.getArgs();
        if (args[0] instanceof EmailData){

        }
    }
}

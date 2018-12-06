package com.sham.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MethedAspect {

    @Before("execution( * com.sham.demo.server.UserService.demo(..))")
    public void aspect(){
        System.out.println(123);
    }
}

package com.sham.demo.aspect;

import com.sham.common.dto.FormData;
import com.sham.common.dto.ParamData;
import com.sham.common.utils.LoggerUtils;
import com.sham.demo.model.SrUser;
import com.sham.demo.server.InfoService;
import com.sham.demo.server.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class DemoAspect {
    @Autowired
    UserService userService;
    @Autowired
    InfoService infoService;

    @Pointcut("execution(* com.sham.demo.controller.UserController.userList(..))")
    public void pointcut() {

    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void handle(JoinPoint joinPoint, Object result) {
        if (result instanceof FormData) {
            FormData formData = ((FormData) result);
            List<SrUser> rows = formData.getRows();
            for (SrUser user : rows) {
                user.setName("iamshamer");
            }
        }
    }

    @Pointcut("execution(* com.sham.demo.controller.UserController.add(..))")
    public void around() {
    }

    @Around(value = "around()")
    public void around(ProceedingJoinPoint point) {
        LoggerUtils.info("before");
        try {
            Object[] args = point.getArgs();
            if (args[0] instanceof SrUser) {
                point.proceed();
                SrUser info = (SrUser) args[0];
                ParamData paramData = new ParamData();
                String text = (String) paramData.get("text");

            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        LoggerUtils.info("after");
    }
}

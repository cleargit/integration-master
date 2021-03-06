package com.sham.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    public static void info(String msg){
        if (msg==null){
            return;
        }
        getLogger().info(msg);
    }
    public static void error(String msg){
        if (msg==null){

        }
        getLogger().error(msg);
    }
    public static Logger getLogger(){
        //利用堆栈方式获取类名
         StackTraceElement[] stackTrace=(new Throwable()).getStackTrace();
        Logger logger=LoggerFactory.getLogger(stackTrace[2].getClassName());
        return logger;
    }
}

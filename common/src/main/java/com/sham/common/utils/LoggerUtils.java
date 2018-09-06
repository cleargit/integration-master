package com.sham.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtils {

    public static void info(String msg){
        if (msg==null){
            return;
        }
        StackTraceElement[] stackTrace=(new Throwable()).getStackTrace();
        Logger logger=LoggerFactory.getLogger(stackTrace[1].getClassName());
        logger.info(msg);
    }
}

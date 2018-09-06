package com.sham.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT="yyyy-MM-dd HH:mm:ss";

    //获取当前时间 秒时间戳
    public static Long getCurrTime(){
        return System.currentTimeMillis()/1000;
    }

    //返回当前时间
    public static String getFmDate(String fm){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(fm);
        Date date=new Date();
        return simpleDateFormat.format(date);
    }


}

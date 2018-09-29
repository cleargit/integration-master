package com.sham.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public static String fmDate(Integer time,String fm){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(fm);
        Date date=new Date(time*1000L);
        return simpleDateFormat.format(date);
    }
    //返回一个有多少天
    public static Integer getMonthDay(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    //获取当前年份
    public static Integer getYear(){
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.YEAR);
    }
    //获取当前月
    public static Integer getMonth(){
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.MARCH);
    }
    //获取当前月
    public static Integer getDay(){
        Calendar cal=Calendar.getInstance();
        return cal.get(Calendar.DATE);
    }
    public static Long getRand(int year, int month, int day){
        Calendar cal=Calendar.getInstance();
         cal.set(year,month,day);
         return cal.getTimeInMillis();
    }

    public static void main(String[] args) {
        System.out.println(getDay());
    }

}

package com.sham.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenUtil {

    public static void setVal(String key,Object value){
        setVal(key,value,ComUtil.getRequest());
    }
    public static void setVal(String key, Object value, HttpServletRequest request){
        request.getSession().setAttribute(key,value);
    }

    public static Object getVal(String key){
        return ComUtil.getRequest().getSession().getAttribute(key);
    }

    public static void setCookie(String key, String value){
        setCookie(key,value,ComUtil.getReponse());
    }
    public static void setCookie(String key, String value, HttpServletResponse response){
        Cookie cookie=new Cookie(key,value);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getCookie(String key){
       return getCookie(key,ComUtil.getRequest());
    }
    public static String getCookie(String key,HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(key)){
                return cookie.getValue();
            }
        }
        return null;
    }
}

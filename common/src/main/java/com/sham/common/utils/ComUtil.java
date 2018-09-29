package com.sham.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getReponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static void setRequestAttr(String key, Object value) {
        getRequest().setAttribute(key, value);
    }

    public static Object getRequestAttr(String key) {
        return getRequest().getAttribute(key);
    }

    public static String handleSearch(String param) {
        JSONObject jsonObject = JSON.parseObject(param);
        Iterator<String> iterator = jsonObject.keySet().iterator();
        List<String> list = new ArrayList<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String target=key.split("@")[0];
            String modal = key.split("@")[1];
            Object value = jsonObject.get(key);
            if ("like".equals(modal)) {
                list.add(String.format("%s like ('%s%s%s')", target, "%", value, "%s"));
            } else if ("eq".equals(modal)) {
                list.add(String.format("%s = %s", target, value));
            }

        }
        return StringUtils.join(list," and ");
    }

}

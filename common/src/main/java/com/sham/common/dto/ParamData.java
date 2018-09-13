package com.sham.common.dto;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ParamData extends HashMap {

    private HttpServletRequest request;


    public ParamData() {
        this.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap.size() == 0 && !"application/x-www-form-urlencoded".equals(request.getHeader("Content-Type"))) {
            setParameterMap();
        }
        for (Map.Entry entry : parameterMap.entrySet()) {
            String value = "";
            String name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if (valueObj == null) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value += values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            this.put(name, value);
        }
    }

    public void setParameterMap() {
        try {
            StringBuilder sb = new StringBuilder();
            char[] buff = new char[1024];
            BufferedReader reader = this.request.getReader();
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff,0,len);
            }
            JSONObject jsonObject = (JSONObject) JSONObject.parse(sb.toString());
            super.putAll(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.sham.common.dto;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParamData extends HashMap {

    private HttpServletRequest request;

    public ParamData() {
        this.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        for(Map.Entry entry:parameterMap.entrySet()){
            String value="";
            String  name= (String) entry.getKey();
             Object valueObj=entry.getValue();
           if (valueObj==null){
               value="";
           }else if (valueObj instanceof String[]){
               String[] values= (String[]) valueObj;
               for (int i = 0; i <values.length ; i++) {
                   value+=values[i]+",";
               }
               value=value.substring(0,value.length()-1);
           }else {
               value=valueObj.toString();
           }
           this.put(name,value);
        }
    }
}

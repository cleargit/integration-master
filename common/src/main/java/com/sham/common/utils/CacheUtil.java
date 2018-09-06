package com.sham.common.utils;

import com.sham.common.Cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;

public class CacheUtil {

    @Autowired
    CacheService cacheService;

    public Object getValue(String key){
        return cacheService.getValue(key);
    }
    public String getValue2String(String key){
        Object value=cacheService.getValue(key);
        if (value!=null){
            return String.valueOf(cacheService.getValue(key));
        }
        return "";
    }

}

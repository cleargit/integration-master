package com.sham.common.Cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CacheData {

    @Autowired
    CacheService cacheService;

    public Object getValue(String key) {
        return cacheService.getValue(key);
    }

    public String getValue2String(String key) {
        Object value = cacheService.getValue(key);
        if (value != null) {
            return String.valueOf(cacheService.getValue(key));
        }
        return null;
    }

    public Integer getValue2Int(String key) {
        String value = getValue2String(key);
        if (value != null) {
            return Integer.valueOf(value);
        }
        return null;
    }

}

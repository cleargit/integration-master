package com.sham.common.Cache;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CacheService {

    private Map<String, Object> dataMap = new HashMap<String, Object>();
    @Cacheable(value = "abc",key = "#key")
    public Object getValue(String key){
        return dataMap.get(key);
    }
    @CachePut(value = "abc",key = "#key")
    public Object setValue(String key,Object value){
        dataMap.put(key,value);
        return value;
    }
}

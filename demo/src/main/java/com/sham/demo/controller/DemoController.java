package com.sham.demo.controller;

import com.sham.common.Cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @Autowired
    CacheService cacheService;


    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping(value = "get")
    @ResponseBody
    public Object getCache(String key) {
        Object value=cacheService.getValue(key);
        if (value!=null){
            return value;
        }
        return "error";
    }
    @RequestMapping(value = "set")
    @ResponseBody
    public Object setCache(String key,String val) {
        Object value=cacheService.setValue(key,val);
        if (value!=null){
            return value;
        }
        return "cache ok";
    }
    @RequestMapping(value = "te")
    @ResponseBody
    public Object te(String key,String val) {
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Object value=opsForValue.get(key);
        if (value!=null){
            return value;
        }
        opsForValue.set(key,val);
        return "cache ok ";
    }
}

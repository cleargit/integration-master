package com.sham.demo.controller;

import com.sham.excel.ExeclUtil;
import com.sham.common.Cache.CacheService;
import com.sham.common.annotation.ControllerLog;
import com.sham.common.core.IConfig;
import com.sham.common.dto.ParamData;
import com.sham.common.utils.Iputil;
import com.sham.common.utils.UploadUtil;
import com.sham.demo.dto.DemoBo;
import com.sham.demo.model.SrUser;
import com.sham.demo.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DemoController {
    @Autowired
    CacheService cacheService;


    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserService userService;
    @Value("${upload.dir}")
    String dir;
    @Value("${upload.path}")
    String path;

    @RequestMapping(value = "get")
    @ResponseBody
    public Object getCache(String key) {
        Object value = cacheService.getValue(key);
        if (value != null) {
            return value;
        }
        return "error";
    }

    @RequestMapping(value = "set")
    @ResponseBody
    public Object setCache(String key, String val) {
        Object value = cacheService.setValue(key, val);
        if (value != null) {
            return value;
        }
        return "cache ok";
    }

    @RequestMapping(value = "te")
    @ResponseBody
    public Object te(String key, String val) {
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Object value = opsForValue.get(key);
        if (value != null) {
            return value;
        }
        opsForValue.set(key, val);
        return "cache ok ";
    }

    @RequestMapping(value = "demo")
    @ResponseBody
    public String demo(String start) {
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.exchange("https://douban.uieee.com/v2/movie/top250?start=" + start, HttpMethod.POST, null, String.class);
        ParamData paramData = new ParamData();
        return response.getBody();
    }

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(HttpServletRequest request) throws IOException {
        System.out.println(Iputil.getIp(request));
        return UploadUtil.uploadImg(request, dir, path);
    }

    @RequestMapping(value = "execl")
    @ResponseBody
    public void execl() {
        List<SrUser> list = userService.selectAll();
        Map<String, String> map = new HashMap<>();
        map.put("title", "哈哈");
        ExeclUtil.exportExecl("common-template.xls", list, DemoBo.class, map, "demo");
    }

    @ControllerLog("abc")
    @RequestMapping(value = "config")
    @ResponseBody
    public String getConfig(String name) {
        return IConfig.getConfig(name);
    }
}

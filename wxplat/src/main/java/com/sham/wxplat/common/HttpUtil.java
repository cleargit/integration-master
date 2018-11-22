package com.sham.wxplat.common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class HttpUtil {
    public static JSONObject get(String url) {
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, null, String.class);
        return JSONObject.parseObject(response.getBody());
    }

    public static JSONObject get(String url, Object param){
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, null, String.class);
        return JSONObject.parseObject(response.getBody());
    }
    public static String getForString(String url){
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }
    public static JSONObject post(String url){
        RestTemplate client=new RestTemplate();
        String result=client.postForObject(url, null, String.class);
        return JSONObject.parseObject(result);
    }
    public static JSONObject post(String url, Object param) {
        RestTemplate client = new RestTemplate();
        String result=client.postForObject(url, param, String.class);
        return JSONObject.parseObject(result);
    }
    public static String postForString(String url,Object param){
        RestTemplate client = new RestTemplate();
        return client.postForObject(url, param, String.class);
    }
    public static JSONObject uploadFile(String url, File file) {
        MediaType type = MediaType.parseMediaType("multipart/form-data");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(type);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        FileSystemResource resource = new FileSystemResource(file);
        map.add("media", resource);
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
        RestTemplate client = new RestTemplate();
        String result = client.exchange(url, HttpMethod.POST, httpEntity, String.class).getBody();
        return JSONObject.parseObject(result);
    }
    public static byte[] postForByte(String url,Object param){
        RestTemplate client=new RestTemplate();
        return client.postForObject(url,param,byte[].class);
    }
    public static byte[] getForByte(String url){
        RestTemplate client=new RestTemplate();
        return client.getForObject(url,byte[].class);
    }
}

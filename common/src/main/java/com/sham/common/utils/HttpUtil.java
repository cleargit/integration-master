package com.sham.common.utils;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {

    public static String get(String url){
        RestTemplate client = new RestTemplate();
        ResponseEntity<String> response = client.exchange(url, HttpMethod.GET, null, String.class);
        return response.getBody();
    }
    public static String post(String url,Object  obj){
        RestTemplate client = new RestTemplate();
        return client.postForObject(url, obj, String.class);

    }

}

package com.sham.demo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sham.demo.server.EntityService;
import com.sham.demo.server.UserService;
import com.sham.demo.server.WorkService;
import com.sham.excel.service.ExeclService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    WorkService workService;

    @Autowired
    ExeclService execlService;

    @Autowired
    EntityService entityService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void test() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "click");
        jsonObject.put("name", "查看信息");
        jsonObject.put("key", "info");
    }
}

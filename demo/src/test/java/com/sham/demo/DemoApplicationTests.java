package com.sham.demo;

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
}

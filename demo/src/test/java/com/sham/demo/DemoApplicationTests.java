package com.sham.demo;

import com.sham.demo.dto.DemoBo;
import com.sham.demo.model.SrUser;
import com.sham.demo.server.UserService;
import com.sham.demo.server.WorkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    WorkService workService;

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

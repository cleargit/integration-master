package com.sham.demo;

import com.sham.demo.model.EntityDemo;
import com.sham.demo.server.EntityService;
import com.sham.demo.server.UserService;
import com.sham.demo.server.WorkService;
import com.sham.excel.service.ExeclService;
import com.sham.mongodb.Entity;
import com.sham.mongodb.dao.EntityDao;
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
    public void test1(){
        EntityDemo entity=new EntityDemo();
        entity.setId(99L);
        entity.setDescription("iamshamer");
        entity.setTitle("ti");
        entityService.save(entity);
        EntityDemo result=entityService.findByid(2L);
        EntityDemo result2=entityService.findByid(99L);
        entityService.remove(1L);
        System.out.println();
    }
}

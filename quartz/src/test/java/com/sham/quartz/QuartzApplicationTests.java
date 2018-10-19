package com.sham.quartz;

import com.sham.quartz.service.QuartzJobService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzApplicationTests {

    @Autowired
    QuartzJobService quartzJobService;
    @Test
    public void contextLoads() {
        Object o=quartzJobService.selectAll();
        System.out.println();
    }

}

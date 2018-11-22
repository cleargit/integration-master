package com.sham.quartz.service;

import com.sham.common.annotation.QuartzJob;
import com.sham.common.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    QuartzJobService quartzJobService;
//    @QuartzJob(title = "title",cron = "0/10 * * * * ?")
    public void demo(){
        LoggerUtils.info("IAMSHAMER");
    }
    @QuartzJob(title = "title",cron = "0/10 * * * * ?")
    public void l(){
        Object o=quartzJobService.selectAll();
        System.out.println(123);
    }
}

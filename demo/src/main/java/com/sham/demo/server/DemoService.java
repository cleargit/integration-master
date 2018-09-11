package com.sham.demo.server;

import com.sham.common.annotation.Initialize;

import com.sham.common.utils.LoggerUtils;

import com.sham.demo.model.Demo;
import com.sham.mybatis.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class DemoService extends AbstractService<Demo> {

    @Initialize
    public void init() {
        LoggerUtils.info("测试");
        Object o=selectAll();
        System.out.println();
    }

}

package com.sham.server;

import com.sham.common.annotation.Initialize;
import com.sham.common.utils.LoggerUtils;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Initialize
    public void init() {
        LoggerUtils.info("测试");

    }

}

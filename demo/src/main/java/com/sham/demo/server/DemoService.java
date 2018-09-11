package com.sham.demo.server;

import com.sham.common.annotation.Initialize;
import com.sham.common.dto.WebSocketData;
import com.sham.common.utils.LoggerUtils;
import com.sham.mybatis.service.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class DemoService extends AbstractService<WebSocketData> {
    @Initialize
    public void init() {
        LoggerUtils.info("测试");

    }

}

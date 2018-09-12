package com.sham.demo.server;

import com.sham.common.annotation.Initialize;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Initialize
    public void init2() {
        System.out.println(456);
    }

}

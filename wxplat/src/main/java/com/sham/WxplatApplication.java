package com.sham;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.sham.*.mapper")
@SpringBootApplication
@EnableCaching
public class WxplatApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxplatApplication.class, args);
    }
}

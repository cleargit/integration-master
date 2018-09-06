package com.sham.common.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AppCore {
    @Autowired
    private static AppCore instance;

    @Autowired
    private Environment env;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static Environment getEnv(){
        return instance.env;
    }
}

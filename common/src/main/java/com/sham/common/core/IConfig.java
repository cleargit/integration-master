package com.sham.common.core;

import com.sham.common.utils.LoggerUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Properties;

public class IConfig {


    private static final String FILE_NAME = "/config.properties";
    private static Properties prop = null;

    static {
        prop = new Properties();
        try {
            prop.load(IConfig.class.getResourceAsStream(FILE_NAME));
        } catch (IOException e) {
            LoggerUtils.info("读取配置失败");
            e.printStackTrace();
        }
    }


    public static String getConfig(String key) {
        String result = AppCore.getEnv().getProperty(key);
        if (!StringUtils.isEmpty(result)) {
            return result;
        }
        return prop.getProperty(key);
    }


}

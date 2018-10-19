package com.sham.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public SpringUtil() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        System.out.println(applicationContext.containsBean(beanName));
        System.out.println(applicationContext.containsBean(beanName.toLowerCase()));
        return applicationContext.containsBean(beanName) ? (T) applicationContext.getBean(beanName) : null;
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> baseType) {
        return applicationContext.getBeansOfType(baseType);
    }

}


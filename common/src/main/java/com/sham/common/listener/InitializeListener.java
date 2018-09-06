package com.sham.common.listener;

import com.sham.common.annotation.Initialize;
import com.sham.common.dto.AnnotationContain;
import com.sham.common.processor.ListenerProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.List;

@Configuration
public class InitializeListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<AnnotationContain> containList = ListenerProcessor.getAnnotationByclazz(Initialize.class);
        if (containList!=null){
        for (AnnotationContain contain : containList) {
            try {
                contain.getMethod().invoke(contain.getBean());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }}
    }
}

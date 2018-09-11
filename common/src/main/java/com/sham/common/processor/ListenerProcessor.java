package com.sham.common.processor;

import com.sham.common.dto.AnnotationContain;
import com.sham.common.utils.ClassUtil;
import com.sham.common.utils.LoggerUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ListenerProcessor implements BeanPostProcessor {
    private static Map<String,List<AnnotationContain>> contain=new HashMap<>();
    private static List<Class> classes = new ArrayList<>();

    static {
        List<Class<?>> allClass = ClassUtil.getclass("com.sham.common.annotation");
        for (Class clas : allClass) {
            classes.add(clas);
        }
    }
    public static <T extends Annotation> List<AnnotationContain> getAnnotationByclazz(Class<T> annotationcl){
        String key=annotationcl.getName();
        if (contain.containsKey(key)){
            return contain.get(key);
        }
        return null;
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Method[] methods=ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (methods!=null){
        for (Method method: methods ) {
            for (Class cl:classes) {
                Annotation annotation=AnnotationUtils.findAnnotation(method,cl);
                String key=cl.getName();
                if (annotation!=null){
                    List<AnnotationContain> list=new ArrayList<>();
                    if (contain.containsKey(key)){
                        list=contain.get(key);
                    }
                    list.add(new AnnotationContain(annotation,bean,method));
                    contain.put(key,list);
                }
            }}
        }
        return bean;
    }
}

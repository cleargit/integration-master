package com.sham.common.dto;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationContain {
    private Annotation annotation;
    private Object bean;
    private Method method;

    public AnnotationContain(Annotation annotation, Object bean, Method method) {
        this.annotation = annotation;
        this.bean = bean;
        this.method = method;
    }

    public Annotation getAnnotation() {
        return annotation;
    }

    public void setAnnotation(Annotation annotation) {
        this.annotation = annotation;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}

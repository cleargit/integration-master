package com.sham.excel;

public class ExeclHeader {
    private String title;
    private Integer order;
    private Integer width;
    private String methodName;

    public ExeclHeader(String title, int order, int width) {
        this.title = title;
        this.order = order;
        this.width = width;
    }

    public ExeclHeader(String title, Integer order, Integer width, String methodName) {
        this.title = title;
        this.order = order;
        this.width = width;
        this.methodName = methodName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}

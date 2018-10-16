package com.sham.demo.bo;

import com.sham.demo.model.Work;

public class WorkBo extends Work {
    private String name;
    private String day;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}

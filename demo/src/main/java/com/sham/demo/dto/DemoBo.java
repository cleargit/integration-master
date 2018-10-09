package com.sham.demo.dto;


import com.sham.common.annotation.ExportCsv;

public class DemoBo {
    private String rank;
    private String name;


    @ExportCsv(title = "名字", order = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ExportCsv(title = "排位", order = 1)
    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}


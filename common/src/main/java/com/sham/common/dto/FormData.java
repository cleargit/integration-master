package com.sham.common.dto;

import com.sham.common.base.BaseModel;

import java.util.List;

public class FormData<T extends BaseModel> {
    private Integer page;
    private Integer count;
    private Integer total;
    private List<T> rows;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}

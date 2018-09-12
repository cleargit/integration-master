package com.sham.common.dto;

public class AjaxResult  {
    private Integer code;
    private String msg;

    public AjaxResult(String msg) {
        this.code=200;
        this.msg = msg;
    }


    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

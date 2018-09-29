package com.sham.demo.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

public class Work extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userId")
    private Integer userid;

    private Integer date;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return userId
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * @return date
     */
    public Integer getDate() {
        return date;
    }

    /**
     * @param date
     */
    public void setDate(Integer date) {
        this.date = date;
    }
}
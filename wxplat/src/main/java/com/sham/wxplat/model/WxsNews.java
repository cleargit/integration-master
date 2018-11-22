package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_news")
public class WxsNews extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 标题
     */
    private String title;

    /**
     * 简要
     */
    private String description;

    /**
     * 封面图片地址
     */
    private String picurl;

    /**
     * 跳转地址
     */
    private String url;

    /**
     * 创建人
     */
    @Column(name = "createdBy")
    private Integer createdby;

    /**
     * 创建时间
     */
    @Column(name = "createdAt")
    private Integer createdat;

    /**
     * 更新人
     */
    @Column(name = "updatedBy")
    private Integer updatedby;

    /**
     * 更新时间
     */
    @Column(name = "updatedAt")
    private Integer updatedat;

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
     * 获取模板名称
     *
     * @return name - 模板名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置模板名称
     *
     * @param name 模板名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取简要
     *
     * @return description - 简要
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置简要
     *
     * @param description 简要
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取封面图片地址
     *
     * @return picurl - 封面图片地址
     */
    public String getPicurl() {
        return picurl;
    }

    /**
     * 设置封面图片地址
     *
     * @param picurl 封面图片地址
     */
    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    /**
     * 获取跳转地址
     *
     * @return url - 跳转地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置跳转地址
     *
     * @param url 跳转地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取创建人
     *
     * @return createdBy - 创建人
     */
    public Integer getCreatedby() {
        return createdby;
    }

    /**
     * 设置创建人
     *
     * @param createdby 创建人
     */
    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    /**
     * 获取创建时间
     *
     * @return createdAt - 创建时间
     */
    public Integer getCreatedat() {
        return createdat;
    }

    /**
     * 设置创建时间
     *
     * @param createdat 创建时间
     */
    public void setCreatedat(Integer createdat) {
        this.createdat = createdat;
    }

    /**
     * 获取更新人
     *
     * @return updatedBy - 更新人
     */
    public Integer getUpdatedby() {
        return updatedby;
    }

    /**
     * 设置更新人
     *
     * @param updatedby 更新人
     */
    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    /**
     * 获取更新时间
     *
     * @return updatedAt - 更新时间
     */
    public Integer getUpdatedat() {
        return updatedat;
    }

    /**
     * 设置更新时间
     *
     * @param updatedat 更新时间
     */
    public void setUpdatedat(Integer updatedat) {
        this.updatedat = updatedat;
    }
}
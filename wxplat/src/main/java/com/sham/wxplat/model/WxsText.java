package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_text")
public class WxsText extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 模板名称
     */
    private String name;

    /**
     * 回复
     */
    private String text;

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
     * 获取回复
     *
     * @return text - 回复
     */
    public String getText() {
        return text;
    }

    /**
     * 设置回复
     *
     * @param text 回复
     */
    public void setText(String text) {
        this.text = text;
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
package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_reply")
public class WxsReply extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 回复模板id
     */
    @Column(name = "templateId")
    private String templateid;

    /**
     * 收到
     */
    private String receive;

    @Column(name = "inDate")
    private Integer indate;

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
     * 获取回复模板id
     *
     * @return templateId - 回复模板id
     */
    public String getTemplateid() {
        return templateid;
    }

    /**
     * 设置回复模板id
     *
     * @param templateid 回复模板id
     */
    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }

    /**
     * 获取收到
     *
     * @return receive - 收到
     */
    public String getReceive() {
        return receive;
    }

    /**
     * 设置收到
     *
     * @param receive 收到
     */
    public void setReceive(String receive) {
        this.receive = receive;
    }

    /**
     * @return inDate
     */
    public Integer getIndate() {
        return indate;
    }

    /**
     * @param indate
     */
    public void setIndate(Integer indate) {
        this.indate = indate;
    }
}
package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_event")
public class WxsEvent extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 事件
     */
    private String event;

    /**
     * 消息类型类型
     */
    @Column(name = "msgType")
    private String msgtype;

    /**
     * 如回复图文 多个以 , 分

     */
    @Column(name = "templateId")
    private String templateid;

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
     * 获取事件
     *
     * @return event - 事件
     */
    public String getEvent() {
        return event;
    }

    /**
     * 设置事件
     *
     * @param event 事件
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * 获取消息类型类型
     *
     * @return msgType - 消息类型类型
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * 设置消息类型类型
     *
     * @param msgtype 消息类型类型
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    /**
     * 获取如回复图文 多个以 , 分

     *
     * @return templateId - 如回复图文 多个以 , 分

     */
    public String getTemplateid() {
        return templateid;
    }

    /**
     * 设置如回复图文 多个以 , 分

     *
     * @param templateid 如回复图文 多个以 , 分

     */
    public void setTemplateid(String templateid) {
        this.templateid = templateid;
    }
}
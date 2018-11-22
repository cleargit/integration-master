package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_send_record")
public class WxsSendRecord extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mediaid;

    /**
     * 类型
     */
    private String type;

    /**
     * 文本
     */
    private String text;

    @Column(name = "toUser")
    private String touser;

    /**
     * 已发送msgId
     */
    @Column(name = "msgId")
    private String msgid;

    /**
     * 状态1 :已发送 0 :撤回
     */
    private Integer status;

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
     * @return mediaid
     */
    public String getMediaid() {
        return mediaid;
    }

    /**
     * @param mediaid
     */
    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取文本
     *
     * @return text - 文本
     */
    public String getText() {
        return text;
    }

    /**
     * 设置文本
     *
     * @param text 文本
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return toUser
     */
    public String getTouser() {
        return touser;
    }

    /**
     * @param touser
     */
    public void setTouser(String touser) {
        this.touser = touser;
    }

    /**
     * 获取已发送msgId
     *
     * @return msgId - 已发送msgId
     */
    public String getMsgid() {
        return msgid;
    }

    /**
     * 设置已发送msgId
     *
     * @param msgid 已发送msgId
     */
    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    /**
     * 获取状态1 :已发送 0 :撤回
     *
     * @return status - 状态1 :已发送 0 :撤回
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态1 :已发送 0 :撤回
     *
     * @param status 状态1 :已发送 0 :撤回
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}
package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_access_token")
public class WxsAccessToken extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户openId
     */
    @Column(name = "openId")
    private String openid;

    /**
     * 用户刷新access_token
     */
    @Column(name = "refreshToken")
    private String refreshtoken;

    /**
     * 过期时间
     */
    private Integer expire;

    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;

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
     * 获取用户openId
     *
     * @return openId - 用户openId
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置用户openId
     *
     * @param openid 用户openId
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取用户刷新access_token
     *
     * @return refreshToken - 用户刷新access_token
     */
    public String getRefreshtoken() {
        return refreshtoken;
    }

    /**
     * 设置用户刷新access_token
     *
     * @param refreshtoken 用户刷新access_token
     */
    public void setRefreshtoken(String refreshtoken) {
        this.refreshtoken = refreshtoken;
    }

    /**
     * 获取过期时间
     *
     * @return expire - 过期时间
     */
    public Integer getExpire() {
        return expire;
    }

    /**
     * 设置过期时间
     *
     * @param expire 过期时间
     */
    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    /**
     * 获取用户授权的作用域，使用逗号（,）分隔
     *
     * @return scope - 用户授权的作用域，使用逗号（,）分隔
     */
    public String getScope() {
        return scope;
    }

    /**
     * 设置用户授权的作用域，使用逗号（,）分隔
     *
     * @param scope 用户授权的作用域，使用逗号（,）分隔
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
}
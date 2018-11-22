package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_user")
public class WxsUser extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * openId
     */
    private String openid;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户性别（1：男性，2：女性）
     */
    private Integer sex;

    /**
     * 订阅时间
     */
    @Column(name = "subscribeTime")
    private Integer subscribetime;

    /**
     * 所在国家
     */
    private String country;

    /**
     * 所在省份
     */
    private String province;

    /**
     * 所在地区
     */
    private String city;

    /**
     * 用户头像
     */
    private String headimgurl;

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
     * 获取openId
     *
     * @return openid - openId
     */
    public String getOpenid() {
        return openid;
    }

    /**
     * 设置openId
     *
     * @param openid openId
     */
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    /**
     * 获取用户昵称
     *
     * @return nickname - 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置用户昵称
     *
     * @param nickname 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取用户性别（1：男性，2：女性）
     *
     * @return sex - 用户性别（1：男性，2：女性）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置用户性别（1：男性，2：女性）
     *
     * @param sex 用户性别（1：男性，2：女性）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取订阅时间
     *
     * @return subscribeTime - 订阅时间
     */
    public Integer getSubscribetime() {
        return subscribetime;
    }

    /**
     * 设置订阅时间
     *
     * @param subscribetime 订阅时间
     */
    public void setSubscribetime(Integer subscribetime) {
        this.subscribetime = subscribetime;
    }

    /**
     * 获取所在国家
     *
     * @return country - 所在国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置所在国家
     *
     * @param country 所在国家
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取所在省份
     *
     * @return province - 所在省份
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置所在省份
     *
     * @param province 所在省份
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取所在地区
     *
     * @return city - 所在地区
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置所在地区
     *
     * @param city 所在地区
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取用户头像
     *
     * @return headimgurl - 用户头像
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 设置用户头像
     *
     * @param headimgurl 用户头像
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }
}
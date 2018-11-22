package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_mpnews")
public class WxsMpnews extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String mediaid;

    /**
     * 所在位置
     */
    private Integer num;

    /**
     * 标题
     */
    private String title;

    /**
     * 简要
     */
    private String digest;

    /**
     * 封面图片URL
     */
    @Column(name = "thumbUrl")
    private String thumburl;

    /**
     * 封面图片mediaId
     */
    @Column(name = "thumbMediaId")
    private String thumbmediaid;

    /**
     * 作者
     */
    private String author;

    /**
     * 内容url
     */
    private String url;

    /**
     * 原文地址URL
     */
    @Column(name = "contentSourceUrl")
    private String contentsourceurl;

    /**
     * 是否显示封面
     */
    @Column(name = "showCoverPic")
    private Integer showcoverpic;

    /**
     * 内容
     */
    private String content;

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
     * 获取所在位置
     *
     * @return num - 所在位置
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置所在位置
     *
     * @param num 所在位置
     */
    public void setNum(Integer num) {
        this.num = num;
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
     * @return digest - 简要
     */
    public String getDigest() {
        return digest;
    }

    /**
     * 设置简要
     *
     * @param digest 简要
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * 获取封面图片URL
     *
     * @return thumbUrl - 封面图片URL
     */
    public String getThumburl() {
        return thumburl;
    }

    /**
     * 设置封面图片URL
     *
     * @param thumburl 封面图片URL
     */
    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    /**
     * 获取封面图片mediaId
     *
     * @return thumbMediaId - 封面图片mediaId
     */
    public String getThumbmediaid() {
        return thumbmediaid;
    }

    /**
     * 设置封面图片mediaId
     *
     * @param thumbmediaid 封面图片mediaId
     */
    public void setThumbmediaid(String thumbmediaid) {
        this.thumbmediaid = thumbmediaid;
    }

    /**
     * 获取作者
     *
     * @return author - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取内容url
     *
     * @return url - 内容url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置内容url
     *
     * @param url 内容url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取原文地址URL
     *
     * @return contentSourceUrl - 原文地址URL
     */
    public String getContentsourceurl() {
        return contentsourceurl;
    }

    /**
     * 设置原文地址URL
     *
     * @param contentsourceurl 原文地址URL
     */
    public void setContentsourceurl(String contentsourceurl) {
        this.contentsourceurl = contentsourceurl;
    }

    /**
     * 获取是否显示封面
     *
     * @return showCoverPic - 是否显示封面
     */
    public Integer getShowcoverpic() {
        return showcoverpic;
    }

    /**
     * 设置是否显示封面
     *
     * @param showcoverpic 是否显示封面
     */
    public void setShowcoverpic(Integer showcoverpic) {
        this.showcoverpic = showcoverpic;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}
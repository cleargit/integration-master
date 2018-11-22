package com.sham.wxplat.dto;

import com.alibaba.fastjson.annotation.JSONField;

public class WxsMpnewsDto {
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
    @JSONField(name = "thumb_url")
    private String thumburl;

    /**
     * 封面图片mediaId
     */
    @JSONField(name = "thumb_media_id")
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
    @JSONField(name = "content_source_url")
    private String contentsourceurl;

    /**
     * 是否显示封面
     */
    @JSONField(name = "show_cover_pic")
    private Integer showcoverpic;

    /**
     * 内容
     */
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMediaid() {
        return mediaid;
    }

    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getThumburl() {
        return thumburl;
    }

    public void setThumburl(String thumburl) {
        this.thumburl = thumburl;
    }

    public String getThumbmediaid() {
        return thumbmediaid;
    }

    public void setThumbmediaid(String thumbmediaid) {
        this.thumbmediaid = thumbmediaid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentsourceurl() {
        return contentsourceurl;
    }

    public void setContentsourceurl(String contentsourceurl) {
        this.contentsourceurl = contentsourceurl;
    }

    public Integer getShowcoverpic() {
        return showcoverpic;
    }

    public void setShowcoverpic(Integer showcoverpic) {
        this.showcoverpic = showcoverpic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

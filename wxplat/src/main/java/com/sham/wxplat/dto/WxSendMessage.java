package com.sham.wxplat.dto;


import com.sham.wxplat.common.MessageUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class WxSendMessage {
    // 发送方的账号
    protected String FromUserName;
    // 接收方的账号(OpenID)
    protected String ToUserName;
    // 消息创建时间
    protected Long CreateTime;
    /**
     * 消息类型
     * text 文本消息
     * image 图片消息
     * voice 语音消息
     * video 视频消息
     * music 音乐消息
     * news 图文消息
     */
    protected String MsgType;
    // 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
    @XmlElementWrapper(name = "Image")
    private String[] MediaId;
    // 文本内容
    private String Content;

    private Integer ArticleCount;
    @XmlElementWrapper(name = "Articles")
    private List<WxNewsTemplate> item;

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public Long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String[] getMediaId() {
        return MediaId;
    }

    public void setMediaId(String[] mediaId) {
        MediaId = mediaId;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(Integer articleCount) {
        ArticleCount = articleCount;
    }

    public List<WxNewsTemplate> getItem() {
        return item;
    }

    public void setItem(List<WxNewsTemplate> item) {
        this.item = item;
    }

    public void sendNews(List<WxNewsTemplate> list, int size) {
        this.setItem(list);
        this.setArticleCount(list.size());
        this.setMsgType(MessageUtil.MESSAGE_NEWS);
    }

    public void sendText(String text) {
        this.setMsgType(MessageUtil.MESSAGEX_TEXT);
        this.setContent(text);
    }
}

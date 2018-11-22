package com.sham.wxplat.model;

import com.sham.common.base.BaseModel;
import javax.persistence.*;

@Table(name = "wxs_menu")
public class WxsMenu extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parentId")
    private Integer parentid;

    /**
     * 类型
     */
    private String type;

    /**
     * 菜单名字
     */
    private String name;

    /**
     * 菜单key
     */
    @Column(name = "menuKey")
    private String menukey;

    /**
     * 消息类型
     */
    @Column(name = "msgType")
    private String msgtype;

    /**
     * 类型为view 需要 
     */
    private String url;

    /**
     * 如回复图文 多个以 , 分

     */
    @Column(name = "templateId")
    private String templateid;

    /**
     * 图文素材id
     */
    private String mediaid;

    /**
     * 排序
     */
    private Integer sort;

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
     * @return parentId
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * @param parentid
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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
     * 获取菜单名字
     *
     * @return name - 菜单名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置菜单名字
     *
     * @param name 菜单名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取菜单key
     *
     * @return menuKey - 菜单key
     */
    public String getMenukey() {
        return menukey;
    }

    /**
     * 设置菜单key
     *
     * @param menukey 菜单key
     */
    public void setMenukey(String menukey) {
        this.menukey = menukey;
    }

    /**
     * 获取消息类型
     *
     * @return msgType - 消息类型
     */
    public String getMsgtype() {
        return msgtype;
    }

    /**
     * 设置消息类型
     *
     * @param msgtype 消息类型
     */
    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    /**
     * 获取类型为view 需要 
     *
     * @return url - 类型为view 需要 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置类型为view 需要 
     *
     * @param url 类型为view 需要 
     */
    public void setUrl(String url) {
        this.url = url;
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

    /**
     * 获取图文素材id
     *
     * @return mediaid - 图文素材id
     */
    public String getMediaid() {
        return mediaid;
    }

    /**
     * 设置图文素材id
     *
     * @param mediaid 图文素材id
     */
    public void setMediaid(String mediaid) {
        this.mediaid = mediaid;
    }

    /**
     * 获取排序
     *
     * @return sort - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
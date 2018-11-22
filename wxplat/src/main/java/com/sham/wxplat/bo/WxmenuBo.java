package com.sham.wxplat.bo;


import com.sham.wxplat.model.WxsMenu;

import java.util.List;

public class WxmenuBo extends WxsMenu {
    private String key;
    private String media_id;
    private List<WxmenuBo> children;
    private List<WxmenuBo> sub_button;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public List<WxmenuBo> getChildren() {
        return children;
    }

    public void setChildren(List<WxmenuBo> children) {
        this.children = children;
    }

    public List<WxmenuBo> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<WxmenuBo> sub_button) {
        this.sub_button = sub_button;
    }
}

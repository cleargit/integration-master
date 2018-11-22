package com.sham.wxplat.bo;

import com.sham.wxplat.model.WxsMpnews;

import java.util.List;

public class MpnewsBo extends WxsMpnews {
    private List<MpnewsBo> children;

    public List<MpnewsBo> getChildren() {
        return children;
    }

    public void setChildren(List<MpnewsBo> children) {
        this.children = children;
    }
}

package com.sham.wxplat.service;

import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.model.WxsText;
import org.springframework.stereotype.Service;

@Service
public class TextService extends AbstractService<WxsText> {

    public String getText(String[] ids) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            sb.append(super.selectByPrimaryKey(Integer.parseInt(ids[i])).getText());
            sb.append("\n");
        }
        return sb.toString();
    }
}

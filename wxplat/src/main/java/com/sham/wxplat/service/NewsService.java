package com.sham.wxplat.service;

import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.dto.WxNewsTemplate;
import com.sham.wxplat.model.WxsNews;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService extends AbstractService<WxsNews> {

    @Autowired
    GroupSendService groupSendService;

    public List<WxNewsTemplate> getWxmenuList(String[] ids) {
        List<WxsNews> list = new ArrayList<>();
        for (String id : ids) {
            list.add(super.selectByPrimaryKey(Integer.parseInt(id)));
        }
        List<WxNewsTemplate> newsList = new ArrayList<>();
        for (WxsNews news : list) {
            WxNewsTemplate item = new WxNewsTemplate();
            BeanUtils.copyProperties(news, item);
            item.setPicUrl(news.getPicurl());
            newsList.add(item);
        }
        return newsList;
    }
}

package com.sham.wxplat.service;


import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.common.MessageUtil;
import com.sham.wxplat.dto.WxInMessage;
import com.sham.wxplat.dto.WxNewsTemplate;
import com.sham.wxplat.dto.WxSendMessage;
import com.sham.wxplat.model.WxsEvent;
import com.sham.wxplat.model.WxsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventService extends AbstractService<WxsEvent> {

    @Autowired
    WxUserService wxUserService;

    @Autowired
    TextService textService;

    @Autowired
    NewsService newsService;

    @Autowired
    WxMenuService menuService;

    public void handle(WxSendMessage wxSendMessage, WxInMessage message) {
        String event = message.getEvent();
        if (event.equals(MessageUtil.EVENT_SUBSCRIBE)) {
            WxsEvent wxsEvent = getevent(event);
            String msgType = wxsEvent.getMsgtype();
            String[] ids = wxsEvent.getTemplateid().split(",");
            setData(msgType, ids, wxSendMessage);
            wxUserService.insertWxUser(message.getToUserName());
        } else if (event.equals(MessageUtil.EVENT_CLICK)) {
            WxsMenu wxsMenu = menuService.getMenuByKey(message.getEventKey());
            String menukey = wxsMenu.getMenukey();
            WxsMenu target = menuService.getMenuByKey(menukey);
            String msgType = wxsMenu.getMsgtype();
            String[] ids = target.getTemplateid().split(",");
            setData(msgType, ids, wxSendMessage);
        }
    }


    public void setData(String msgType, String[] ids, WxSendMessage wxSendMessage) {
        if (MessageUtil.MESSAGEX_TEXT.equals(msgType)) {
            String text = textService.getText(ids);
            wxSendMessage.sendText(text);
        } else if (MessageUtil.MESSAGE_NEWS.equals(msgType)) {
            List<WxNewsTemplate> newsList = newsService.getWxmenuList(ids);
            wxSendMessage.sendNews(newsList, newsList.size());
        }
    }

    public WxsEvent getevent(String event) {
        Map<String, Object> param = new HashMap<>();
        param.put("where_sql", String.format("event ='%s'", event));
        return super.findOne(param);
    }

}

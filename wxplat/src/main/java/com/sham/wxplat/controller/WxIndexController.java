package com.sham.wxplat.controller;


import com.sham.wxplat.common.MessageUtil;
import com.sham.wxplat.common.SignUtil;
import com.sham.wxplat.common.WxConfig;
import com.sham.wxplat.dto.WxInMessage;
import com.sham.wxplat.dto.WxSendMessage;
import com.sham.wxplat.service.EventService;
import com.sham.wxplat.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class WxIndexController {


    @Autowired
    ReplyService replyService;

    @Autowired
    EventService eventService;

    @RequestMapping(value = "wxindex", method = RequestMethod.GET)
    public String index(@RequestParam("signature") String signature,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("nonce") String nonce,
                        @RequestParam("echostr") String echostr) {
        String sortString = SignUtil.sort(WxConfig.TOKEN, timestamp, nonce);
        String myString = SignUtil.sha1(sortString);
        if (myString != null && myString != "" && myString.equals(signature)) {
            System.out.println("签名校验通过");
            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return echostr;
        } else {
            System.out.println("签名校验失败");
            return "";
        }
    }

    @RequestMapping(value = "wxindex", method = RequestMethod.POST, produces = {MediaType.TEXT_XML_VALUE})
    public Object message(@RequestBody WxInMessage message) {
        WxSendMessage wxSendMessage = new WxSendMessage();
        wxSendMessage.setToUserName(message.getFromUserName());
        wxSendMessage.setFromUserName(message.getToUserName());
        wxSendMessage.setCreateTime(new Date().getTime());
        String type = message.getMsgType();
        wxSendMessage.setMsgType(type);
        if (MessageUtil.MESSAGEX_TEXT.equals(type)) {  //处理收到文本
            String content = message.getContent();
            String text = replyService.replay(content);
            wxSendMessage.setContent(text);
        } else if (MessageUtil.MESSAGE_IMAGE.equals(type)) {  //处理收到图片
            wxSendMessage.setMediaId(new String[]{message.getMediaId()});
        } else if (MessageUtil.MESSAGEX_EVENT.equals(type)) { //处理事件
            eventService.handle(wxSendMessage, message);
        }
        return wxSendMessage;
    }
}

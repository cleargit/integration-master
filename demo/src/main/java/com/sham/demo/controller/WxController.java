package com.sham.demo.controller;

import com.sham.common.base.BaseController;
import com.sham.common.dto.WxInMessage;
import com.sham.common.dto.WxSendMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

@RestController
@RequestMapping("wxapi")
public class WxController extends BaseController {

    public static final String TOKEN = "iamshamer";

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(@RequestParam("signature") String signature,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("nonce") String nonce,
                        @RequestParam("echostr") String echostr) {
        //排序
        String sortString = sort(TOKEN, timestamp, nonce);
        //加密
        String myString = sha1(sortString);
        //校验
        if (myString != null && myString != "" && myString.equals(signature)) {
            System.out.println("签名校验通过");
            //如果检验成功原样返回echostr，微信服务器接收到此输出，才会确认检验完成。
            return echostr;
        } else {
            System.out.println("签名校验失败");
            return "";
        }
    }

    @RequestMapping(value = "index", method = RequestMethod.POST, produces = {MediaType.TEXT_XML_VALUE})
    @ResponseBody
    public Object message(@RequestBody WxInMessage message) {
        WxSendMessage wxSendMessage = new WxSendMessage();
        wxSendMessage.setToUserName(message.getFromUserName());
        wxSendMessage.setFromUserName(message.getToUserName());
        wxSendMessage.setCreateTime(new Date().getTime());
        String type = message.getMsgType();
        wxSendMessage.setMsgType(type);
        if ("text".equals(type)) {
            String content = message.getContent();
            String text;
            if ("hello".equals(content)) {
                text = "hello iamshamer\n" +
                        "i want increase cash";
            } else {
                text = content;
            }
            wxSendMessage.setContent(text);
        } else if ("image".equals(type)) {
            wxSendMessage.setMediaId(new String[]{message.getMediaId()});
        } else if ("event".equals(type)) {
            if ("subscribe".equals(message.getEvent())) {
                wxSendMessage.setMsgType("text");
                wxSendMessage.setContent("欢迎 ！");
            }
        }
        return wxSendMessage;
    }

    public String sort(String token, String timestamp, String nonce) {
        String[] strArray = {token, timestamp, nonce};
        Arrays.sort(strArray);
        StringBuilder sb = new StringBuilder();
        for (String str : strArray) {
            sb.append(str);
        }

        return sb.toString();
    }

    public String sha1(String str) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
package com.sham.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sham.common.core.IConfig;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WxUtil {

    private static String APPID;
    private static String APPSECRET;

    @PostConstruct
    public void init() {
        LoggerUtils.info("初始化wx...");
        APPID = IConfig.getConfig("wx.appid");
        APPSECRET = IConfig.getConfig("wx.appsecret");
    }

    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    public static String getAccsessToken() {
        String url = GET_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
        String body = HttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(body);
        return jsonObject.getString("access_token");
    }

    public static void setMenu(Object object) {
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", "14_erH7XWWFUqCWIQXKhRydzxkvuQkoVp7pAyaNS9Thhbf5ahjwhAcJyiTwxVqAw5s0Fv0dVuXFyJV3UK6gLhezZ38R3MlTUAqnFRJ2DBMP2BOQGUcmHdytTAVisYDI30IrNEXWS6n8wKnVZZMgUIZaADAOCT");
        String result = HttpUtil.post(url, object);
        System.out.println();
    }
}

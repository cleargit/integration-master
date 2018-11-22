package com.sham.wxplat.service;

import com.alibaba.fastjson.JSONObject;
import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.common.WxUtil;
import com.sham.wxplat.model.WxsUser;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
public class WxUserService extends AbstractService<WxsUser> {


    public void insertWxUser(String openId) {
        try {
            JSONObject result = WxUtil.getUserDetail(openId);
            WxsUser wxsUser = JSONObject.toJavaObject(result, WxsUser.class);
            wxsUser.setSubscribetime(result.getInteger("subscribe_time"));
            super.insertSelective(wxsUser);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}

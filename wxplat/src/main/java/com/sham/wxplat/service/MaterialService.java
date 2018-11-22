package com.sham.wxplat.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.common.MessageUtil;
import com.sham.wxplat.common.WxUtil;
import com.sham.wxplat.model.WxsMaterial;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class MaterialService extends AbstractService<WxsMaterial> {
    public void getAndInsert() {
        JSONObject jsonObject = WxUtil.getList(MessageUtil.MESSAGE_IMAGE);
        JSONArray array = (JSONArray) jsonObject.get("item");
        Iterator iterator = array.iterator();
        while (iterator.hasNext()) {
            JSONObject json = (JSONObject) iterator.next();
            WxsMaterial material = JSONObject.toJavaObject(json, WxsMaterial.class);
            super.insertSelective(material);
        }
    }

    public Integer insertMaterial(WxsMaterial info) {


        return super.insertSelective(info);

    }
}

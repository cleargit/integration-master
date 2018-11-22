package com.sham.wxplat.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sham.common.dto.FormData;
import com.sham.common.dto.ParamData;
import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.bo.MpnewsBo;
import com.sham.wxplat.common.MessageUtil;
import com.sham.wxplat.common.WxUtil;
import com.sham.wxplat.dto.WxsMpnewsDto;
import com.sham.wxplat.model.WxsMpnews;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MpNewsService extends AbstractService<WxsMpnews> {


    public FormData dataList(String mediaid) {
        Map<String, Object> map = new HashMap<>();
        map.put("where_sql", "num = 0");
        if (mediaid != null) {
            map.put("where_sql", String.format("num = 0 and mediaid='%s'", mediaid));
        }
        FormData ret = super.dataList(map);
        List<MpnewsBo> rows = (List<MpnewsBo>) ret.getRows();
        Map<String, List<MpnewsBo>> listMap = new HashMap<>();
        for (MpnewsBo item : rows) {
            map.put("where_sql", String.format("mediaid= '%s' and num !=0 ", item.getMediaid()));
            map.put("order_sql", "order by num asc");
            List<MpnewsBo> list = super.findForList(map);
            item.setChildren(list);
        }
        return ret;
    }

    public void getAndInsert() {
        JSONObject result = WxUtil.getList(MessageUtil.MESSAGE_NEWS);
        JSONArray array = (JSONArray) result.get("item");
        for (Object item : array) {
            JSONObject jsonObject = ((JSONObject) item);
            String media_id = (String) jsonObject.get("media_id");
            JSONObject content = (JSONObject) jsonObject.get("content");
            JSONArray news = (JSONArray) content.get("news_item");
            for (int i = 0; i < news.size(); i++) {
                JSONObject json = ((JSONObject) news.get(i));
                WxsMpnewsDto wxsMpnewsDto = JSON.toJavaObject(json, WxsMpnewsDto.class);
                wxsMpnewsDto.setNum(i);
                wxsMpnewsDto.setMediaid(media_id);
                WxsMpnews info = new WxsMpnews();
                BeanUtils.copyProperties(wxsMpnewsDto, info);
                super.insertSelective(info);
            }
        }
    }

    public Integer insertMpnews(List<WxsMpnews> WxsMpnews) {
        Integer num = 0;
        if (WxsMpnews.size() > 0) {
            List<WxsMpnewsDto> list = new ArrayList<>();
            JSONObject param = new JSONObject();
            for (WxsMpnews info : WxsMpnews) {
                WxsMpnewsDto mpnewsDto = new WxsMpnewsDto();
                BeanUtils.copyProperties(info, mpnewsDto);
                list.add(mpnewsDto);
            }
            param.put("articles", list);
            System.out.println();
            JSONObject jsonObject = JSONObject.parseObject(param.toString());
            JSONObject result = WxUtil.addMpNews(jsonObject);
            if (result != null) {
                num = getMpnews(result.getString("media_id"));
            }
        }
        return num;
    }

    public Integer getMpnews(String mediaid) {
        JSONObject result = WxUtil.getMpnews(mediaid);
        Integer num = 0;
        if (result != null) {
            JSONArray array = (JSONArray) result.get("news_item");
            for (Object item : array) {
                WxsMpnewsDto mpnewsDto = JSONObject.toJavaObject((JSONObject) item, WxsMpnewsDto.class);
                WxsMpnews info = new WxsMpnews();
                BeanUtils.copyProperties(mpnewsDto, info);
                info.setMediaid(mediaid);
                info.setNum(num);
                num += super.insertSelective(info);
            }
        }
        return num;
    }

    public Boolean updateMpnews(WxsMpnewsDto wxsMpnewsDto) {
        JSONObject param = new JSONObject();
        param.put("media_id", wxsMpnewsDto.getMediaid());
        param.put("index", wxsMpnewsDto.getNum());
        param.put("articles", wxsMpnewsDto);
        JSONObject result = WxUtil.editMpnews(param.toJSONString());
        if (result != null) {
            WxsMpnews info = new WxsMpnews();
            BeanUtils.copyProperties(wxsMpnewsDto, info);
            super.updateSelective(info);
            return true;
        }
        return false;
    }

    public Integer deleteMpnews() {
        ParamData param = new ParamData();
        String ids = "";
        Integer num = 0;
        if (param.containsKey("id")) {
            ids = (String) param.get("id");
            String[] dids = ids.split(",");
            Map<String, Object> map = new HashMap<>();
            for (String id : dids) {
                String mediaid = super.selectByPrimaryKey(Integer.parseInt(id)).getMediaid();
                if (WxUtil.deleteMaterial(mediaid) != null) {
                    map.put("where_sql", String.format("mediaid='%s'", mediaid));
                    num += super.delect("deleteMpnews", map);
                }
            }
        }
        return num;
    }
}

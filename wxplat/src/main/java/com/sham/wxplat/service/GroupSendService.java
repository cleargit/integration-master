package com.sham.wxplat.service;

import com.alibaba.fastjson.JSONObject;
import com.sham.wxplat.common.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupSendService {

    @Autowired
    ReplyService replyService;

    //群发信息
    public String sendAll(String type, String content) {
        JSONObject param = WxUtil.sendAllJson(type, content);
        JSONObject result = WxUtil.sendAll(param);
        return result != null ? result.getString("msg_id") : null;
    }

    //根据用户对应公众号openId 群发
    public String sendOpenId(List<String> list, String type, String content) {
        JSONObject result = WxUtil.sendForOpenId(list, type, content);
        return result != null ? result.getString("msg_id") : null;
    }

    //预览
    public void preview(String type, String content) {
        JSONObject param = WxUtil.previewJson(type, content);
        JSONObject result = WxUtil.sendPreview(param);
        System.out.println(result);
    }


    //删除群发信息
    public void delete(String msgId) {
        this.delete(msgId, null);
    }

    public void delete(String msgId, String articleIdx) {
        JSONObject param = new JSONObject();
        param.put("msg_id", msgId);
        System.out.println(articleIdx);
        if (articleIdx != null) {
            param.put("article_idx", articleIdx);
        }

    }


}

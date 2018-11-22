package com.sham.wxplat.service;

import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.model.WxsReply;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ReplyService extends AbstractService<WxsReply> {

    public String replay(String receive) {
        Map<String, Object> map = new HashMap<>();
        map.put("where_sql", String.format("receive ='%s'", receive));
        WxsReply wxsReply = super.findOne(map);
        if (wxsReply != null) {
            return "";
        }
        return "不能处理的信息";

    }
}

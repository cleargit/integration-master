package com.sham.wxplat.service;

import com.alibaba.fastjson.JSONObject;
import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.common.MessageUtil;
import com.sham.wxplat.model.WxsSendRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Service
public class RecordService extends AbstractService<WxsSendRecord> {

    @Autowired
    GroupSendService groupSendService;

    public Boolean sendMsg(WxsSendRecord record) {
        String type = record.getType();
        String touser = record.getTouser();
        String content = getContent(type, record);
        String msgid = "";
        Boolean success = false;
        if ("preview".equals(touser)) {
            groupSendService.preview(type, content);
            success = true;
        } else if ("all".equals(touser)) {
            msgid = groupSendService.sendAll(type, content);
        } else {
            msgid = groupSendService.sendOpenId(Arrays.asList(record.getTouser().split(",")), type, content);
        }
        if (!StringUtils.isEmpty(msgid)) {
            success = true;
            record.setMsgid(msgid);
        }
        insertSelective(record);
        return success;

    }

    public String getContent(String type, WxsSendRecord record) {
        if (type.equals(MessageUtil.MESSAGEX_TEXT)) {
            return record.getText();
        } else if (type.equals(MessageUtil.MESSAGE_MPNEWS)) {
            return record.getMediaid();
        }
        return null;
    }

    public void setData(WxsSendRecord record, JSONObject result) {
        record.setMsgid(result.getString("msg_id"));
    }
}

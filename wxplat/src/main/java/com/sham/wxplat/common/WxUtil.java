package com.sham.wxplat.common;

import com.alibaba.fastjson.JSONObject;

import com.sham.common.utils.LoggerUtils;
import com.sham.common.utils.SpringUtil;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WxUtil {

    public static String getAccessToken() {
        RedisTemplate<String, String> redisTemplate = SpringUtil.getBean("redisTemplate");
        String access_token = redisTemplate.opsForValue().get(WxConfig.REDISKEY);
        if (access_token != null) {
            return access_token;
        }
        String url = WxConfig.GET_ACCESS_TOKEN_URL.replace("APPID", WxConfig.APPID).replace("APPSECRET", WxConfig.APPSECRET);
        JSONObject jsonObject = HttpUtil.get(url);
        access_token = jsonObject.getString("access_token");
        redisTemplate.opsForValue().set(WxConfig.REDISKEY, access_token, 7200, TimeUnit.SECONDS);
        return access_token;
    }

    public static JSONObject sendForOpenId(List<String> touser, String type, String content) {
        JSONObject param = new JSONObject();
        param.put("touser", touser);
        dispose(param, type, content);
        String url = setTokenUrl(WxConfig.SEND_OPENID_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;
    }

    public static JSONObject sendAllJson(String type, String content) {
        return sendAllJson(true, type, content, null);
    }

    public static JSONObject sendAllJson(Boolean sendAll, String type, String content, String tagId) {
        JSONObject param = new JSONObject();
        JSONObject item = new JSONObject();
        if (tagId != null) {
            item.put("tag_id", tagId);
        }
        item.put("is_to_all", sendAll);
        param.put("filter", item);
        dispose(param, type, content);
        return param;
    }

    public static JSONObject dispose(JSONObject param, String type, String content) {
        JSONObject item = new JSONObject();
        String key = "media_id";
        if (MessageUtil.MESSAGEX_TEXT.equals(type)) {
            key = "content";
        } else if (MessageUtil.MESSAGE_MPNEWS.equals(type)) {
            param.put("send_ignore_reprint", 0);
        }
        item.put(key, content);
        param.put(type, item);
        param.put("msgtype", type);
        return param;
    }

    public static JSONObject previewJson(String type, String content) {
        JSONObject param = new JSONObject();
        param.put("touser", WxConfig.MYOPENID);
        dispose(param, type, content);
        return param;
    }

    //群发
    public static JSONObject sendAll(Object param) {
        String url = setTokenUrl(WxConfig.SEND_ALL_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;
    }

    //删除群发
    public static JSONObject deleteSend(String msgid) {
        JSONObject param = new JSONObject();
        param.put("msg_id", msgid);
        String url = setTokenUrl(WxConfig.DELETE_SEND_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;
    }

    //设置菜单
    public static JSONObject setMenu(Object param) {
        String url = setTokenUrl(WxConfig.CREATE_MENU_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;
    }

    //新增图文素材
    public static JSONObject addMpNews(Object param) {
        String url = setTokenUrl(WxConfig.ADD_MPNEWS_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;
    }

    //修改图文素材
    public static JSONObject editMpnews(Object param) {
        String url = setTokenUrl(WxConfig.UPDATE_MPNEWS_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;
    }

    //获取永久素材
    public static JSONObject getMpnews(String mediaId) {
        String url = setTokenUrl(WxConfig.GET_MPNEWS_URL);
        JSONObject param = new JSONObject();
        param.put("media_id", mediaId);
        String result = HttpUtil.postForString(url, param);
        String json = null;
        try {
            json = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(json);
    }

    //删除永久素材
    public static JSONObject deleteMaterial(String mediaid) {
        JSONObject param = new JSONObject();
        param.put("media_id", mediaid);
        String url = setTokenUrl(WxConfig.DELETE_MATERIAL_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;
    }

    //上传封面照片
    public static JSONObject addMaterial(File file) {
        String url = setTokenUrl(WxConfig.ADD_MATERIAL_URL);
        JSONObject result = HttpUtil.uploadFile(url, file);
        return result;
    }

    //上传内容照片
    public static JSONObject uploadImg(File file) {
        String url = setTokenUrl(WxConfig.ADD_IMAGE_URL);
        JSONObject result = HttpUtil.uploadFile(url, file);
        return result;
    }

    //发送预览
    public static JSONObject sendPreview(Object param) {
        String url = setTokenUrl(WxConfig.PREVIEW_URL);
        JSONObject result = HttpUtil.post(url, param);
        if (checkData(result)) {
            return result;
        }
        return null;

    }

    //获取用户
    public static void getUser() {
        String url = setTokenUrl(WxConfig.GET_USER_URL);
        JSONObject result = HttpUtil.get(url);
        System.out.println();
    }

    //获取其他类型素材
    public static byte[] getMeta(String mediaId) {
        String url = setTokenUrl(WxConfig.GET_MATERIAL_URL);
        JSONObject param = new JSONObject();
        param.put("media_id", mediaId);
        byte[] o = HttpUtil.postForByte(url, param);
        System.out.println(o);
        return o;
    }

    //获取素材列表
    public static JSONObject getList(String type) {
        String url = setTokenUrl(WxConfig.GET_MATERIAL_LIST_URL);
        JSONObject param = new JSONObject();
        param.put("type", type);
        param.put("offset", "0");
        param.put("count", "20");
        JSONObject result = HttpUtil.post(url, param);
        return result;
    }

    public static JSONObject getUserDetail(String openId) throws UnsupportedEncodingException {
        String url = setTokenUrl(WxConfig.GET_USERDETAIL_URL, "OPENID", openId);
        String result = HttpUtil.getForString(url);
        String json = new String(result.getBytes("ISO-8859-1"), "UTF-8");
        return JSONObject.parseObject(json);
    }

    public static JSONObject getUserDetailList(Object param) {
        String url = setTokenUrl(WxConfig.GET_USERDETAIL_lIST_URL);
        JSONObject result = HttpUtil.post(url, param);
        return result;
    }

    public static String setTokenUrl(String url) {
        return url.replace("ACCESS_TOKEN", getAccessToken());
    }

    public static String setTokenUrl(String url, String key, String value) {
        if (key != null) {
            url = url.replace(key, value);
        }
        return url.replace("ACCESS_TOKEN", getAccessToken());
    }

    public static Boolean checkData(JSONObject jsonObject) {
        if (jsonObject.getString("media_id") != null || jsonObject.getInteger("errcode") == 0 || jsonObject.getString("url") != null) {
            return true;
        }
        LoggerUtils.error(jsonObject.toJSONString());
        return false;
    }
}

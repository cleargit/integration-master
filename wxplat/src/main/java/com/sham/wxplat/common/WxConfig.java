package com.sham.wxplat.common;

public class WxConfig {

    public static final String MYOPENID = "on_6I1FuciYCtLDIMNct0ncVZjyQ";

    public static final String APPID = "wxf7387403d16b1d72";
    public static final String APPSECRET = "6e260a20e9054713767d1e3a6e4f8aa8";
    public static final String TOKEN = "iamshamer";

    public static final String REDISKEY = "access_token";

    //获取access_token 接口  http请求方式：GET
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    //设置菜单接口 http请求方式：POST
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    //获取菜单 http请求方式：GET
    public static final String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    //删除菜单 http请求方式：GET
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    //上传图文素材接口 http请求方式：POST
    public static final String ADD_MPNEWS_URL = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";

    //修改图文素材接口 http请求方式：POST
    public static final String UPDATE_MPNEWS_URL = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
    //获取永久素材接口
    public static final String GET_MPNEWS_URL="https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
    //删除永久素材 http请求方式: POST
    public static final String DELETE_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";

    //上传图文消息内的图片获取URL http请求方式: POST
    public static final String ADD_IMAGE_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";

    //上传其他类型素材接口  http请求方式：POST
    public static final String ADD_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN&type=image";


    //获取永久素材 http请求方式: POST
    public static final String GET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";

    //获取素材列表 http请求方式: POST
    public static final String GET_MATERIAL_LIST_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";


    //群发接口 http请求方式：POST
    public static final String SEND_ALL_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";

    //删除群发 http请求方式：POST
    public static final String DELETE_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";

    //预览接口 http请求方式：POST
    public static final String PREVIEW_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";


    //根据用户OPENID群发 http请求方式：POST
    public static final String SEND_OPENID_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";

    //获取用户列表 http请求方式: GET
    public static final String GET_USER_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=";

    //获取用户详情 http请求方式: GET
    public static final String GET_USERDETAIL_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    //批量获取用户信息 http请求方式: POST
    public static final String GET_USERDETAIL_lIST_URL = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
}

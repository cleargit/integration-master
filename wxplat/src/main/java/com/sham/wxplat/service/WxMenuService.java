package com.sham.wxplat.service;

import com.alibaba.fastjson.JSONObject;

import com.sham.common.dto.FormData;
import com.sham.mybatis.service.AbstractService;
import com.sham.wxplat.bo.WxmenuBo;
import com.sham.wxplat.common.WxUtil;
import com.sham.wxplat.dto.WxButton;
import com.sham.wxplat.model.WxsMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxMenuService extends AbstractService<WxsMenu> {

    @Autowired
    EventService eventService;

    public FormData datalist() {
        Map<String, Object> map = new HashMap<>();
        map.put("where_sql", "parentId=0");
        map.put("order_sql", "order by sort desc");
        FormData ret = super.dataList(map);
        List<WxmenuBo> rows = (List<WxmenuBo>) ret.getRows();
        for (WxmenuBo item : rows) {
            map.put("where_sql", String.format("parentId = %s", item.getId()));
            List<WxmenuBo> childs = super.findForList(map);
            if (childs != null) {
                item.setChildren(childs);

            }
        }
        return ret;
    }

    public Integer updateMenu(WxsMenu menu) {
        menu.setMenukey("WxMenuKey"+menu.getId());
        Integer num=0;
        num = super.updateSelective(menu);
        setMenu();
        return num;
    }

    public Integer insertMenu(WxsMenu info) {
        Map<String, Object> map = new HashMap<>();
        if (info.getParentid() == null || info.getParentid() == 0) {
            map.put("where_sql", "parentId=0");
            Integer count = (Integer) this.findForObject("findCount", map);
            if (count >= 3) { //一级菜单最多3个
                return -1;
            }
        } else {
            map.put("where_sql", String.format("parentId=%s", info.getParentid()));
            Integer count = (Integer) this.findForObject("findCount", map);
            if (count >= 5) {
                return 0; //二级菜单最多5个
            }
        }
        Integer num = insertSelective(info);
        updateMenu(info);
        return num;
    }

    public void setMenu() {
        List<WxmenuBo> rows = (List<WxmenuBo>) datalist().getRows();
        List<WxButton> list = new ArrayList<>();
        for (WxmenuBo item : rows) {
            if (item.getChildren() != null) {
                item.setSub_button(item.getChildren());
                item.setChildren(null);
            }
        }
        JSONObject param = new JSONObject();
        param.put("button", rows);
        JSONObject result = WxUtil.setMenu(param);

    }

    public List<WxsMenu> parent() {
        Map<String, Object> map = new HashMap<>();
        map.put("where_sql", "parentId=0");
        return super.findForList(map);
    }

    public WxsMenu getMenuByKey(String key) {
        Map<String, Object> param = new HashMap<>();
        param.put("where_sql", String.format("menuKey ='%s'", key));
        return super.findOne(param);
    }
    public Integer deleteMenu(){
        Integer num=super.deleteIds();
        setMenu();
        return num;
    }
}

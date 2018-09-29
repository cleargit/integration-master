package com.sham.demo.server;

import com.sham.common.dto.AjaxResult;
import com.sham.common.utils.DateUtil;
import com.sham.demo.bo.WorkBo;
import com.sham.demo.model.SrUser;
import com.sham.demo.model.Work;
import com.sham.mybatis.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends AbstractService<SrUser> {

    @Autowired
    WorkService workService;

    //自动分配
    public void allot() {
        Integer days = DateUtil.getMonthDay();
        Map<String, Object> map = new HashMap<>();
        Map<Integer, WorkBo> person = workService.getPerson(null);
        map.put("order_sql", "order by weight desc");
        for (int i =DateUtil.getDay()+1; i <=days ; i++) {
            if (!person.containsKey(i)){
                Integer date=workService.getRand(i).intValue();
                SrUser user=findOne(map);
                Work work=new Work();
                work.setUserid(user.getId());
                work.setDate(date);
                workService.insertSelective(work);
                user.setWeight(user.getWeight()-1);
                super.updateSelective(user);
            }
        }
    }
    //对新成员重新分配
    public AjaxResult allot(Integer num){
        if (num==null){
            num=5;
        }
        Integer days = DateUtil.getMonthDay();
        Map<String,Object> map=new HashMap<>();
        map.put("order_sql","order by weight DESC");
        Integer re=DateUtil.getDay()+num;
        if (re>days){
            return new AjaxResult(-1,"已到本月尽头了");
        }
        for (int i = re; i <=days ; i++) {
            Integer date=workService.getRand(i).intValue();
            SrUser user=findOne(map);
            Work work=workService.findByDate(date);
            work.setUserid(user.getId());
            workService.updateSelective(work);
            user.setWeight(user.getWeight()+1);
            super.updateSelective(user);
        }
        return new AjaxResult("已对未来"+num+"天重新分配");
    }
    public Integer insert(SrUser info){
        Map<String,Object> map=new HashMap<>();
        map.put("order_sql", "order by weight desc");
        SrUser user=findOne(map);
        info.setWeight(user.getWeight()+1);
        return super.insertSelective(info);
    }
}

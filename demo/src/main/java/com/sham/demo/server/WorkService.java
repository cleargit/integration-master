package com.sham.demo.server;

import com.sham.common.dto.ParamData;
import com.sham.common.utils.DateUtil;
import com.sham.demo.bo.WorkBo;
import com.sham.demo.model.Work;
import com.sham.mybatis.service.AbstractService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkService extends AbstractService<Work> {
    //获取本月已安排的人数
    public Map<Integer, WorkBo> getPerson(Map<String,Object> map,Boolean ret) {
        if (map==null){
            map=new HashMap<>();
        }
        Integer nowDay = DateUtil.getDay();
        map.put("where_sql", "FROM_UNIXTIME(date,'%Y%m') =DATE_FORMAT( CURDATE( ) , '%Y%m' )");
        List<WorkBo> list = findForList(map);
        Map<Integer, WorkBo> result = new HashMap<>();
        for (WorkBo item : list) {
            Integer day = Integer.parseInt(DateUtil.fmDate(item.getDate(), "dd"));
            if (day >= nowDay || ret) {
                result.put(day, item);
            }

        }
        return result;
    }

    public void insertWork(Work work) {
        Map<String, Object> map = new HashMap<>();
        map.put("where_sql", "TO_DAYS(FROM_UNIXTIME(date))=to_days(" + work.getDate() + ")");
        Work info = findOne(map);
        if (info != null) {
            return;
        }
        insertSelective(work);
    }

    public Long getRand(Integer day) {
        return DateUtil.getRand(DateUtil.getYear(), DateUtil.getMonth(), day) / 1000;
    }

    public Work findByDate(Integer date) {
        Map<String, Object> map = new HashMap<>();
        map.put("where_sql", "TO_DAYS(FROM_UNIXTIME(date))=to_days(FROM_UNIXTIME(" + date + "))");
        return findOne(map);
    }

    public List<WorkBo> getWorkPerson() {
        ParamData paramData=new ParamData();
        String begin="0";
        String end="7";
        if (paramData.containsKey("begin")){
            begin=(String) paramData.get("begin");
        }
        if (paramData.containsKey("end")){
            end= (String) paramData.get("end");
        }
        Map<String,Object> pamam=new HashMap<>();
        pamam.put("limit_sql",String.format("limit %s , %s",begin,end));
        Map<Integer, WorkBo> map= this.getPerson(pamam,true);
        List<WorkBo> list=new ArrayList<>();
        for (Map.Entry<Integer,WorkBo> entry: map.entrySet()) {
            WorkBo workBo=entry.getValue();
            workBo.setDay(DateUtil.fmDate(workBo.getDate(),"dd"));
            list.add(entry.getValue());
        }
        return list;
    }
}

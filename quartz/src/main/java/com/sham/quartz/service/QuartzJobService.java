package com.sham.quartz.service;

import com.sham.mybatis.service.AbstractService;
import com.sham.quartz.model.SysQuartzJob;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuartzJobService extends AbstractService<SysQuartzJob> {

    public SysQuartzJob selectByJobName(String jobName){
        Map<String,Object> map=new HashMap<>();
        map.put("where_sql",String.format("jobName = '%s'",jobName));
        return findOne(map);
    }

    public List<SysQuartzJob> selectByStatus(String status){
        Map<String,Object> map=new HashMap<>();
        map.put("where_sql",String.format("status = %s",status));
        return findForList(map);
    }
}

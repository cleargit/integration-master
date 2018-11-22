package com.sham.quartz.service;

import com.sham.common.utils.LoggerUtils;
import com.sham.common.utils.SpringUtil;
import com.sham.quartz.model.SysQuartzJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;

@Service
public class QuartzJobExecute {


    public void execute(JobExecutionContext context) {
        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        String mapkey = "sham" + key.getGroup() + "_" + key.getName();
        Trigger trigger = context.getTrigger();
        SysQuartzJob scheduleJob = (SysQuartzJob) context.getMergedJobDataMap().get(mapkey);
        LoggerUtils.info("运行任务名称 = [" + scheduleJob + "]");
        Method method = null;
        try {
            Class clazz = Class.forName(scheduleJob.getJobclass());
            Method[] methods = clazz.getMethods();
            String beanName=clazz.getSimpleName();
            Object target = SpringUtil.getBean(beanName.substring(0,1).toLowerCase()+beanName.substring(1));
            for (Method md : methods) {
                if (scheduleJob.getMethodname().equals(md.getName())) {
                    int i = md.getParameterCount();
                    if (i <= 1) {
                        method = md;
                    }
                    break;
                }
            }

            method.invoke(target, null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

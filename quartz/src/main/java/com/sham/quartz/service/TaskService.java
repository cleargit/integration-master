package com.sham.quartz.service;

import com.sham.quartz.job.QuartzJobFactoryDisallowConcurrentExecution;
import com.sham.quartz.model.SysQuartzJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskService {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;


    public void addJob(SysQuartzJob job) {
        String jobName = job.getJobname();
        String jobgroup = job.getJobgroup();
        Scheduler scheduler = getScheduler();
        try {
            JobDetail jobDetail = getjobDetail(job);
            jobDetail.getJobDataMap().put(getDataMapKey(job), job);
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobgroup);
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronexpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey).startAt(job.getStarttime() == null ? new Date() : job.getStarttime()).withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    public String getDataMapKey(SysQuartzJob job) {
        return "sham" + job.getJobgroup() +"_"+ job.getJobname();
    }

    public JobDetail getjobDetail(SysQuartzJob job) {
        return JobBuilder.newJob(QuartzJobFactoryDisallowConcurrentExecution.class).withIdentity(job.getJobname(), job.getJobgroup()).build();
    }

    public Scheduler getScheduler() {
        return this.schedulerFactoryBean.getScheduler();
    }
}

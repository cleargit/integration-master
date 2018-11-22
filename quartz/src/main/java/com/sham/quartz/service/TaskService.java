package com.sham.quartz.service;

import com.sham.quartz.config.JobStatus;
import com.sham.quartz.job.QuartzJobFactoryDisallowConcurrentExecution;
import com.sham.quartz.model.SysQuartzJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    @Autowired
    SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    QuartzJobService service;
    public void edit(SysQuartzJob job) throws SchedulerException {

        int status=job.getStatus();
        if (JobStatus.PAUSE==status){
            this.pauseJob(job);
        }else if (JobStatus.DELETE==status){
            this.deleteJob(job);
        }else if (JobStatus.RESUME==status){
            this.resumeJob(job);
        }else if (JobStatus.RUNNING==status){
            TriggerKey triggerKey=TriggerKey.triggerKey(job.getJobname(),job.getJobgroup());
            CronTrigger trigger= (CronTrigger) this.getScheduler().getTrigger(triggerKey);
            String cronExpression=trigger.getCronExpression();
            if (job.getCronexpression()!=null && !cronExpression.equals(job.getCronexpression()) ){
                this.updateTriggerKey(job);
            }
            if (job.getStarttime()!=null && trigger.getStartTime()!=job.getStarttime()){

            }

        }
        this.service.updateSelective(job);

    }

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


    @Transactional
    public boolean resumeJob(SysQuartzJob job){
        Scheduler scheduler=this.getScheduler();
        JobKey jobKey=JobKey.jobKey(job.getJobname(),job.getJobgroup());
        boolean result;
        try {
            //todo
            scheduler.resumeJob(jobKey);
            result=true;
        } catch (SchedulerException e) {
            result=false;
            e.printStackTrace();
        }
        return result;
    }
    @Transactional
    public boolean pauseJob(SysQuartzJob job){
        Scheduler scheduler=this.getScheduler();
        boolean result;
        JobKey jobKey=JobKey.jobKey(job.getJobname(),job.getJobgroup());
        try {
            scheduler.pauseJob(jobKey);
            result=true;
        } catch (SchedulerException e) {
            result=false;
            e.printStackTrace();
        }
        return result;
    }
    @Transactional
    public boolean deleteJob(SysQuartzJob job){
        Scheduler scheduler=this.getScheduler();
        JobKey jobKey=JobKey.jobKey(job.getJobname(),job.getJobgroup());
        boolean result;
        try {
            scheduler.deleteJob(jobKey);
            result=true;
        } catch (SchedulerException e) {
            result=false;
            e.printStackTrace();
        }
        return result;
    }


    @Transactional
    public void updateTriggerKey(SysQuartzJob job){
        Scheduler scheduler=this.getScheduler();
        JobKey jobKey=JobKey.jobKey(job.getJobname(),job.getJobgroup());
        try {
            TriggerKey triggerKey=TriggerKey.triggerKey(job.getJobname(),job.getJobgroup());
            CronTrigger trigger= (CronTrigger) scheduler.getTrigger(triggerKey);
            CronScheduleBuilder cronScheduleBuilder=CronScheduleBuilder.cronSchedule(job.getCronexpression());
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
            scheduler.rescheduleJob(triggerKey,trigger);
            JobDetail jobDetail=scheduler.getJobDetail(jobKey);
            jobDetail.getJobDataMap().put(this.getDataMapKey(job),job);
            job.setStarttime(trigger.getStartTime());
            job.setNexttime(trigger.getNextFireTime());
            job.setPrevioustime(trigger.getPreviousFireTime());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

  @Transactional
  public void updateStartTime(SysQuartzJob job){
        Scheduler scheduler=this.getScheduler();
        JobKey jobKey=JobKey.jobKey(job.getJobname(),job.getJobgroup());
        TriggerKey triggerKey=TriggerKey.triggerKey(job.getJobname(),job.getJobgroup());
      try {
          CronTriggerImpl trigger= (CronTriggerImpl) scheduler.getTrigger(triggerKey);
          trigger.setStartTime(job.getStarttime());
          scheduler.rescheduleJob(triggerKey,trigger);
          JobDetail jobDetail=scheduler.getJobDetail(jobKey);
          jobDetail.getJobDataMap().put(this.getDataMapKey(job),job);
          job.setStarttime(trigger.getStartTime());
          job.setNexttime(trigger.getNextFireTime());
          job.setPrevioustime(trigger.getPreviousFireTime());
      } catch (SchedulerException e) {
          e.printStackTrace();
      }

  }

    public void getAllJob(){
        Scheduler scheduler=this.getScheduler();
        GroupMatcher groupMatcher=GroupMatcher.anyGroup();
        try {
            Set<JobKey> jobKeySet=scheduler.getJobKeys(groupMatcher);
            Iterator<JobKey> iterator=jobKeySet.iterator();
            while (iterator.hasNext()){
                JobKey jobKey=iterator.next();
                List triggers=scheduler.getTriggersOfJob(jobKey);
                Iterator iterator1=triggers.iterator();
                while (iterator1.hasNext()){
                    Trigger trigger= (Trigger) iterator1.next();

                }
            }
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

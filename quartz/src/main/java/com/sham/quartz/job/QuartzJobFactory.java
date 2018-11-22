package com.sham.quartz.job;

import com.sham.quartz.service.QuartzJobExecute;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class QuartzJobFactory implements Job {
    @Autowired
    QuartzJobExecute jobExecute;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        jobExecute.execute(jobExecutionContext);
    }
}

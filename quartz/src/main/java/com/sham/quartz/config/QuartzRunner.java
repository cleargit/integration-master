package com.sham.quartz.config;

import com.sham.common.annotation.QuartzJob;
import com.sham.common.dto.AnnotationContain;
import com.sham.common.processor.ListenerProcessor;
import com.sham.quartz.model.SysQuartzJob;
import com.sham.quartz.service.QuartzJobService;
import com.sham.quartz.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;


@Component
public class QuartzRunner implements CommandLineRunner {

    @Autowired
    QuartzJobService quartzJobService;

    @Autowired
    TaskService taskService;
    //容器加载完执行
    @Override
    public void run(String... args) throws Exception {
        List<AnnotationContain> containList=ListenerProcessor.getAnnotationByclazz(QuartzJob.class);
        Iterator<AnnotationContain> iterator=containList.iterator();
        while (iterator.hasNext()){
            AnnotationContain annotationContain=iterator.next();
            QuartzJob quartzJob= (QuartzJob) annotationContain.getAnnotation();
            String clazz=annotationContain.getBean().getClass().getName();
            String method=annotationContain.getMethod().getName();
            String jobName=clazz+"->"+method;
            SysQuartzJob info=quartzJobService.selectByJobName(jobName);
            if (info==null){
                SysQuartzJob job = new SysQuartzJob();
                job.setJobname(jobName);
                job.setJobclass(clazz);
                job.setMethodname(method);
                job.setDescription(quartzJob.title());
                job.setCronexpression(quartzJob.cron());
                job.setJobgroup("group-1");
                job.setIsconcurrent((byte)1);
                job.setStatus((byte)1);
                this.quartzJobService.insertSelective(job);
            }
        }
        List<SysQuartzJob> tasks=quartzJobService.selectByStatus("1");

        for (SysQuartzJob job:tasks) {
            taskService.addJob(job);
            quartzJobService.updateSelective(job);
        }


    }
}

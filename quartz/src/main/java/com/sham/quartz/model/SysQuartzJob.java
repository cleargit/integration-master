package com.sham.quartz.model;

import com.sham.common.base.BaseModel;
import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_quartz_job")
public class SysQuartzJob extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jobName")
    private String jobname;

    @Column(name = "jobGroup")
    private String jobgroup;

    private String description;

    @Column(name = "cronExpression")
    private String cronexpression;

    @Column(name = "isConcurrent")
    private Byte isconcurrent;

    @Column(name = "jobClass")
    private String jobclass;

    @Column(name = "methodName")
    private String methodname;

    /**
     * 参数
     */
    private String params;

    @Column(name = "jobStatus")
    private String jobstatus;

    private Byte status;

    @Column(name = "nextTime")
    private Date nexttime;

    @Column(name = "previousTime")
    private Date previoustime;

    @Column(name = "beanName")
    private String beanname;

    @Column(name = "startTime")
    private Date starttime;

    @Column(name = "createdBy")
    private Integer createdby;

    @Column(name = "createdAt")
    private Integer createdat;

    @Column(name = "updatedBy")
    private Integer updatedby;

    @Column(name = "updatedAt")
    private Integer updatedat;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return jobName
     */
    public String getJobname() {
        return jobname;
    }

    /**
     * @param jobname
     */
    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    /**
     * @return jobGroup
     */
    public String getJobgroup() {
        return jobgroup;
    }

    /**
     * @param jobgroup
     */
    public void setJobgroup(String jobgroup) {
        this.jobgroup = jobgroup;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return cronExpression
     */
    public String getCronexpression() {
        return cronexpression;
    }

    /**
     * @param cronexpression
     */
    public void setCronexpression(String cronexpression) {
        this.cronexpression = cronexpression;
    }

    /**
     * @return isConcurrent
     */
    public Byte getIsconcurrent() {
        return isconcurrent;
    }

    /**
     * @param isconcurrent
     */
    public void setIsconcurrent(Byte isconcurrent) {
        this.isconcurrent = isconcurrent;
    }

    /**
     * @return jobClass
     */
    public String getJobclass() {
        return jobclass;
    }

    /**
     * @param jobclass
     */
    public void setJobclass(String jobclass) {
        this.jobclass = jobclass;
    }

    /**
     * @return methodName
     */
    public String getMethodname() {
        return methodname;
    }

    /**
     * @param methodname
     */
    public void setMethodname(String methodname) {
        this.methodname = methodname;
    }

    /**
     * 获取参数
     *
     * @return params - 参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置参数
     *
     * @param params 参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * @return jobStatus
     */
    public String getJobstatus() {
        return jobstatus;
    }

    /**
     * @param jobstatus
     */
    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return nextTime
     */
    public Date getNexttime() {
        return nexttime;
    }

    /**
     * @param nexttime
     */
    public void setNexttime(Date nexttime) {
        this.nexttime = nexttime;
    }

    /**
     * @return previousTime
     */
    public Date getPrevioustime() {
        return previoustime;
    }

    /**
     * @param previoustime
     */
    public void setPrevioustime(Date previoustime) {
        this.previoustime = previoustime;
    }

    /**
     * @return beanName
     */
    public String getBeanname() {
        return beanname;
    }

    /**
     * @param beanname
     */
    public void setBeanname(String beanname) {
        this.beanname = beanname;
    }

    /**
     * @return startTime
     */
    public Date getStarttime() {
        return starttime;
    }

    /**
     * @param starttime
     */
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    /**
     * @return createdBy
     */
    public Integer getCreatedby() {
        return createdby;
    }

    /**
     * @param createdby
     */
    public void setCreatedby(Integer createdby) {
        this.createdby = createdby;
    }

    /**
     * @return createdAt
     */
    public Integer getCreatedat() {
        return createdat;
    }

    /**
     * @param createdat
     */
    public void setCreatedat(Integer createdat) {
        this.createdat = createdat;
    }

    /**
     * @return updatedBy
     */
    public Integer getUpdatedby() {
        return updatedby;
    }

    /**
     * @param updatedby
     */
    public void setUpdatedby(Integer updatedby) {
        this.updatedby = updatedby;
    }

    /**
     * @return updatedAt
     */
    public Integer getUpdatedat() {
        return updatedat;
    }

    /**
     * @param updatedat
     */
    public void setUpdatedat(Integer updatedat) {
        this.updatedat = updatedat;
    }
}
package com.tiktok.task.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;
import sun.dc.pr.PRError;

/**
 * 用户接取任务对象 tk_task_acceptances
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TkTaskAcceptances extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 接取任务的唯一标识符 */
    private Long id;

    /** 接取任务的用户ID */
    @Excel(name = "接取任务的用户ID")
    private Long uid;

    /** 关联的任务ID */
    @Excel(name = "关联的任务ID")
    private Long taskId;

    /** 接取任务的状态 */
    @Excel(name = "接取任务的状态")
    private String status;

    /** 提交的图片链接 */
    @Excel(name = "提交的图片链接")
    private String submittedImage;

    /** 提交时间 */
    @JsonFormat(pattern = "MM/dd/yyyy")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date submissionTime;

    /** 审核通过时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核通过时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvalTime;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date createTime;

    private String username;
    private String taskTitle;
    private String taskRewardAmount;
    private String taskLevel;
    /**
     * 提示音
     */
    private String tips;


    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setSubmittedImage(String submittedImage) 
    {
        this.submittedImage = submittedImage;
    }

    public String getSubmittedImage() 
    {
        return submittedImage;
    }
    public void setSubmissionTime(Date submissionTime) 
    {
        this.submissionTime = submissionTime;
    }

    public Date getSubmissionTime() 
    {
        return submissionTime;
    }
    public void setApprovalTime(Date approvalTime) 
    {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTime() 
    {
        return approvalTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("taskId", getTaskId())
            .append("status", getStatus())
            .append("submittedImage", getSubmittedImage())
            .append("submissionTime", getSubmissionTime())
            .append("approvalTime", getApprovalTime())
            .toString();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskRewardAmount() {
        return taskRewardAmount;
    }

    public void setTaskRewardAmount(String taskRewardAmount) {
        this.taskRewardAmount = taskRewardAmount;
    }

    public String getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(String taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

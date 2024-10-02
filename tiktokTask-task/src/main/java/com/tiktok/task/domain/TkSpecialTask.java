package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 特殊任务触发对象 tk_special_task
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
public class TkSpecialTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 任务id */
    @Excel(name = "任务id")
    private Long taskId;

    /** userid */
    @Excel(name = "userid")
    private Long userId;

    /** 触发次数 */
    @Excel(name = "触发次数")
    private Long triggerCount;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setTriggerCount(Long triggerCount) 
    {
        this.triggerCount = triggerCount;
    }

    public Long getTriggerCount() 
    {
        return triggerCount;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("triggerCount", getTriggerCount())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

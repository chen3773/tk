package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 任务数量对象 tk_tasknum
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
public class TkTasknum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户唯一标识 */
    @Excel(name = "用户唯一标识")
    private Long userId;

    /** 隐藏任务数 */
    @Excel(name = "隐藏任务数")
    private Long hiddenTaskCount;

    /** 普通任务数 */
    @Excel(name = "普通任务数")
    private Long normalTaskCount;

    /** 体验任务数 */
    @Excel(name = "体验任务数")
    private Long experienceTaskCount;

    /** 完成任务总数 */
    @Excel(name = "完成任务总数")
    private Long totalCompletedTasks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setHiddenTaskCount(Long hiddenTaskCount) 
    {
        this.hiddenTaskCount = hiddenTaskCount;
    }

    public Long getHiddenTaskCount() 
    {
        return hiddenTaskCount;
    }
    public void setNormalTaskCount(Long normalTaskCount) 
    {
        this.normalTaskCount = normalTaskCount;
    }

    public Long getNormalTaskCount() 
    {
        return normalTaskCount;
    }
    public void setExperienceTaskCount(Long experienceTaskCount) 
    {
        this.experienceTaskCount = experienceTaskCount;
    }

    public Long getExperienceTaskCount() 
    {
        return experienceTaskCount;
    }
    public void setTotalCompletedTasks(Long totalCompletedTasks) 
    {
        this.totalCompletedTasks = totalCompletedTasks;
    }

    public Long getTotalCompletedTasks() 
    {
        return totalCompletedTasks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("hiddenTaskCount", getHiddenTaskCount())
            .append("normalTaskCount", getNormalTaskCount())
            .append("experienceTaskCount", getExperienceTaskCount())
            .append("totalCompletedTasks", getTotalCompletedTasks())
            .toString();
    }
}

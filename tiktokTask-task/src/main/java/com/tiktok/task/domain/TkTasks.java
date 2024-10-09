package com.tiktok.task.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 任务列对象 tk_tasks
 *
 * @author ruoyi
 * @date 2024-09-25
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TkTasks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务的唯一标识符 */
    private Long id;

    /** 任务标题 */
    @Excel(name = "任务标题")
    private String title;

    /** 任务图片链接 */
    @Excel(name = "任务图片链接")
    private String image;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String description;

    /** 任务的详细描述 */
    @Excel(name = "任务的详细描述")
    private String detailedDescription;

    /** 奖励金额 */
    @Excel(name = "奖励金额")
    private BigDecimal rewardAmount;

    /** 任务链接 */
    @Excel(name = "任务链接")
    private String link;

    /** 任务总数量 */
    @Excel(name = "任务总数量")
    private Long totalQuantity;
    /**
     * 剩余数量
     */
    private int surplusquantity;

    /** 已完成数量 */
    @Excel(name = "已完成数量")
    private Long completedQuantity;

    /** 任务等级 */
    @Excel(name = "任务等级")
    private Long taskLevel;

    /** 任务创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "任务创建时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /** 状态 */
    @Excel(name = "状态")
    private String state;

    private String deleted;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }
    public void setImage(String image)
    {
        this.image = image;
    }

    public String getImage()
    {
        return image;
    }
    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
    public void setDetailedDescription(String detailedDescription)
    {
        this.detailedDescription = detailedDescription;
    }

    public String getDetailedDescription()
    {
        return detailedDescription;
    }
    public void setRewardAmount(BigDecimal rewardAmount)
    {
        this.rewardAmount = rewardAmount;
    }

    public BigDecimal getRewardAmount()
    {
        return rewardAmount;
    }
    public void setLink(String link)
    {
        this.link = link;
    }

    public String getLink()
    {
        return link;
    }
    public void setTotalQuantity(Long totalQuantity)
    {
        this.totalQuantity = totalQuantity;
    }

    public Long getTotalQuantity()
    {
        return totalQuantity;
    }
    public void setCompletedQuantity(Long completedQuantity)
    {
        this.completedQuantity = completedQuantity;
    }

    public Long getCompletedQuantity()
    {
        return completedQuantity;
    }
    public void setTaskLevel(Long taskLevel)
    {
        this.taskLevel = taskLevel;
    }

    public Long getTaskLevel()
    {
        return taskLevel;
    }
    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("title", getTitle())
                .append("image", getImage())
                .append("description", getDescription())
                .append("detailedDescription", getDetailedDescription())
                .append("rewardAmount", getRewardAmount())
                .append("link", getLink())
                .append("totalQuantity", getTotalQuantity())
                .append("completedQuantity", getCompletedQuantity())
                .append("taskLevel", getTaskLevel())
                .append("createdAt", getCreatedAt())
                .append("state", getState())
                .toString();
    }

    public int getSurplusquantity() {
        return surplusquantity;
    }

    public void setSurplusquantity(int surplusquantity) {
        this.surplusquantity = surplusquantity;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
}

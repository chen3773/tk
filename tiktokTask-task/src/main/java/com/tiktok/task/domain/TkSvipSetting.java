package com.tiktok.task.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * svip默认配置对象 tk_svip_setting
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
public class TkSvipSetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 等级 */
    @Excel(name = "等级")
    private Long vipLevel;

    /** 每日任务数量 */
    @Excel(name = "每日任务数量")
    private Long dailyTaskCount;

    /** 升级所需金额 */
    @Excel(name = "升级所需金额")
    private BigDecimal upgradeAmount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVipLevel(Long vipLevel) 
    {
        this.vipLevel = vipLevel;
    }

    public Long getVipLevel() 
    {
        return vipLevel;
    }
    public void setDailyTaskCount(Long dailyTaskCount) 
    {
        this.dailyTaskCount = dailyTaskCount;
    }

    public Long getDailyTaskCount() 
    {
        return dailyTaskCount;
    }
    public void setUpgradeAmount(BigDecimal upgradeAmount) 
    {
        this.upgradeAmount = upgradeAmount;
    }

    public BigDecimal getUpgradeAmount() 
    {
        return upgradeAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("vipLevel", getVipLevel())
            .append("dailyTaskCount", getDailyTaskCount())
            .append("upgradeAmount", getUpgradeAmount())
            .toString();
    }
}

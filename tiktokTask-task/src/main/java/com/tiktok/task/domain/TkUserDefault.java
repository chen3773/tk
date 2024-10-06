package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 系统默认配置对象 tk_user_default
 * 
 * @author ruoyi
 * @date 2024-10-06
 */
public class TkUserDefault extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 体验任务数 */
    @Excel(name = "体验任务数")
    private Long experienceTaskCount;

    /** 普通任务数 */
    @Excel(name = "普通任务数")
    private Long regularTaskCount;

    /** 隐藏任务数 */
    @Excel(name = "隐藏任务数")
    private Long hiddenTaskCount;

    /** 最低提现金额 */
    @Excel(name = "最低提现金额")
    private String minimumWithdrawalAmount;

    /** 区块链名称 */
    @Excel(name = "区块链名称")
    private String blockchain;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setExperienceTaskCount(Long experienceTaskCount) 
    {
        this.experienceTaskCount = experienceTaskCount;
    }

    public Long getExperienceTaskCount() 
    {
        return experienceTaskCount;
    }
    public void setRegularTaskCount(Long regularTaskCount) 
    {
        this.regularTaskCount = regularTaskCount;
    }

    public Long getRegularTaskCount() 
    {
        return regularTaskCount;
    }
    public void setHiddenTaskCount(Long hiddenTaskCount) 
    {
        this.hiddenTaskCount = hiddenTaskCount;
    }

    public Long getHiddenTaskCount() 
    {
        return hiddenTaskCount;
    }
    public void setMinimumWithdrawalAmount(String minimumWithdrawalAmount) 
    {
        this.minimumWithdrawalAmount = minimumWithdrawalAmount;
    }

    public String getMinimumWithdrawalAmount() 
    {
        return minimumWithdrawalAmount;
    }
    public void setBlockchain(String blockchain) 
    {
        this.blockchain = blockchain;
    }

    public String getBlockchain() 
    {
        return blockchain;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("experienceTaskCount", getExperienceTaskCount())
            .append("regularTaskCount", getRegularTaskCount())
            .append("hiddenTaskCount", getHiddenTaskCount())
            .append("minimumWithdrawalAmount", getMinimumWithdrawalAmount())
            .append("blockchain", getBlockchain())
            .toString();
    }
}

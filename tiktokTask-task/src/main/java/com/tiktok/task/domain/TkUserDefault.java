package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 系统默认配置对象 tk_user_default
 * 
 * @author ruoyi
 * @date 2024-09-27
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

    /** vip等级数量 */
    @Excel(name = "vip等级数量")
    private Long vip1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long vip2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long vip3;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long vip4;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long vip5;

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
    public void setVip1(Long vip1) 
    {
        this.vip1 = vip1;
    }

    public Long getVip1() 
    {
        return vip1;
    }
    public void setVip2(Long vip2) 
    {
        this.vip2 = vip2;
    }

    public Long getVip2() 
    {
        return vip2;
    }
    public void setVip3(Long vip3) 
    {
        this.vip3 = vip3;
    }

    public Long getVip3() 
    {
        return vip3;
    }
    public void setVip4(Long vip4) 
    {
        this.vip4 = vip4;
    }

    public Long getVip4() 
    {
        return vip4;
    }
    public void setVip5(Long vip5) 
    {
        this.vip5 = vip5;
    }

    public Long getVip5() 
    {
        return vip5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("experienceTaskCount", getExperienceTaskCount())
            .append("regularTaskCount", getRegularTaskCount())
            .append("hiddenTaskCount", getHiddenTaskCount())
            .append("minimumWithdrawalAmount", getMinimumWithdrawalAmount())
            .append("vip1", getVip1())
            .append("vip2", getVip2())
            .append("vip3", getVip3())
            .append("vip4", getVip4())
            .append("vip5", getVip5())
            .toString();
    }
}

package com.tiktok.task.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 上下分日志对象 tk_transactions
 * 
 * @author ruoyi
 * @date 2024-10-05
 */
public class TkTransactions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符 */
    private Long id;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 增加/扣除 */
    @Excel(name = "增加/扣除")
    private String changeType;

    /** 可提现/不可提现 */
    @Excel(name = "可提现/不可提现")
    private String withdrawable;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date transactionTime;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setChangeType(String changeType) 
    {
        this.changeType = changeType;
    }

    public String getChangeType() 
    {
        return changeType;
    }
    public void setWithdrawable(String withdrawable) 
    {
        this.withdrawable = withdrawable;
    }

    public String getWithdrawable() 
    {
        return withdrawable;
    }
    public void setTransactionTime(Date transactionTime) 
    {
        this.transactionTime = transactionTime;
    }

    public Date getTransactionTime() 
    {
        return transactionTime;
    }
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("amount", getAmount())
            .append("description", getDescription())
            .append("userId", getUserId())
            .append("changeType", getChangeType())
            .append("withdrawable", getWithdrawable())
            .append("transactionTime", getTransactionTime())
            .append("createdAt", getCreatedAt())
            .toString();
    }
}

package com.tiktok.task.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 提现记录对象 tk_Withdrawals
 * 
 * @author ruoyi
 * @date 2024-10-03
 */
public class TkWithdrawals extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 自增主键 */
    private Long id;

    /** 用户 ID */
    @Excel(name = "用户 ID")
    private Long uid;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 区块链地址 */
    @Excel(name = "区块链地址")
    private String address;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private BigDecimal amount;

    /** 提现时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提现时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date withdrawalTime;

    /** 提现状态 */
    @Excel(name = "提现状态")
    private Long status;

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
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setWithdrawalTime(Date withdrawalTime) 
    {
        this.withdrawalTime = withdrawalTime;
    }

    public Date getWithdrawalTime() 
    {
        return withdrawalTime;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("username", getUsername())
            .append("address", getAddress())
            .append("amount", getAmount())
            .append("withdrawalTime", getWithdrawalTime())
            .append("status", getStatus())
            .toString();
    }
}

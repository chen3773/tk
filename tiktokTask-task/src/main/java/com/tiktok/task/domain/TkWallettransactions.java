package com.tiktok.task.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 用户钱包交易记录对象 tk_WalletTransactions
 *
 * @author ruoyi
 * @date 2024-09-24
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TkWallettransactions extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 交易ID，自动递增 */
    private Long id;

    /** 用户ID，用于标识每个用户 */
    @Excel(name = "用户ID，用于标识每个用户")
    private Long userid;

    /** 交易类型（如提现、返利等） */
    @Excel(name = "交易类型", readConverterExp = "如=提现、返利等")
    private String transactionType;

    /** 交易金额（正数收入，负数支出） */
    @Excel(name = "交易金额", readConverterExp = "正=数收入，负数支出")
    private BigDecimal Amount;

    /** 交易日期和时间 */
    @JsonFormat(pattern = "yyyy-MM-dd ")
    @Excel(name = "交易日期和时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;

    /** 交易后余额 */
    @Excel(name = "交易后余额")
    private BigDecimal fundBalance;

    /** 交易描述/备注（可选） */
    @Excel(name = "交易描述/备注", readConverterExp = "可=选")
    private String Description;

    /** 交易类别（收入或提现） */
    @Excel(name = "交易类别", readConverterExp = "收=入或提现")
    private String Category;

    /** 交易状态（如待处理、已完成、失败等） */
    @Excel(name = "交易状态", readConverterExp = "如=待处理、已完成、失败等")
    private String transactionStatus;

    /** 记录最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;

    private String orderNumber;
    private String withdraw;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setUserid(Long userid)
    {
        this.userid = userid;
    }

    public Long getUserid()
    {
        return userid;
    }
    public void setTransactionType(String transactionType)
    {
        this.transactionType = transactionType;
    }

    public String getTransactionType()
    {
        return transactionType;
    }
    public void setAmount(BigDecimal Amount)
    {
        this.Amount = Amount;
    }

    public BigDecimal getAmount()
    {
        return Amount;
    }
    public void setTransactionDate(Date transactionDate)
    {
        this.transactionDate = transactionDate;
    }

    public Date getTransactionDate()
    {
        return transactionDate;
    }
    public void setFundBalance(BigDecimal fundBalance)
    {
        this.fundBalance = fundBalance;
    }

    public BigDecimal getFundBalance()
    {
        return fundBalance;
    }
    public void setDescription(String Description)
    {
        this.Description = Description;
    }

    public String getDescription()
    {
        return Description;
    }
    public void setCategory(String Category)
    {
        this.Category = Category;
    }

    public String getCategory()
    {
        return Category;
    }
    public void setTransactionStatus(String transactionStatus)
    {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionStatus()
    {
        return transactionStatus;
    }
    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userid", getUserid())
                .append("transactionType", getTransactionType())
                .append("Amount", getAmount())
                .append("transactionDate", getTransactionDate())
                .append("fundBalance", getFundBalance())
                .append("Description", getDescription())
                .append("Category", getCategory())
                .append("transactionStatus", getTransactionStatus())
                .append("updatedAt", getUpdatedAt())
                .toString();
    }
}


package com.tiktok.task.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 邀请记录对象 tk_invitation
 *
 * @author ruoyi
 * @date 2024-09-23
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TkInvitation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 唯一标识符 */
    private Long id;

    /** 邀请人ID */
    @Excel(name = "邀请人ID")
    private Long inviterId;

    /** 被邀请人ID */
    @Excel(name = "被邀请人ID")
    private Long inviteeId;

    /** 邀请层级 */
    @Excel(name = "邀请层级")
    private Long level;

    /** 返佣金额 */
    @Excel(name = "返佣金额")
    private String amount;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setInviterId(Long inviterId)
    {
        this.inviterId = inviterId;
    }

    public Long getInviterId()
    {
        return inviterId;
    }
    public void setInviteeId(Long inviteeId)
    {
        this.inviteeId = inviteeId;
    }

    public Long getInviteeId()
    {
        return inviteeId;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }

    public Long getLevel()
    {
        return level;
    }
    public void setAmount(String amount)
    {
        this.amount = amount;
    }

    public String getAmount()
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("inviterId", getInviterId())
                .append("inviteeId", getInviteeId())
                .append("level", getLevel())
                .append("amount", getAmount())
                .toString();
    }
}

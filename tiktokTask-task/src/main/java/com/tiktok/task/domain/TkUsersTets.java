package com.tiktok.task.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.TreeEntity;

/**
 * 用户信息对象 tk_users
 * 
 * @author ruoyi
 * @date 2024-10-02
 */
public class TkUsersTets extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long uid;

    /** 账户名 */
    @Excel(name = "账户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** svip等级 */
    @Excel(name = "svip等级")
    private String svipLevel;

    /** 总奖励金额 */
    @Excel(name = "总奖励金额")
    private String totareward;

    /** 余额 */
    @Excel(name = "余额")
    private String balance;

    /** 不可提现余额 */
    @Excel(name = "不可提现余额")
    private String nonWithdrawableBalance;

    /** 提现USDT地址 */
    @Excel(name = "提现USDT地址")
    private String usdtAddress;

    /** 注册时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date registrationTime;

    /** 头像 */
    @Excel(name = "头像")
    private String avatar;

    /** 推荐人ID */
    @Excel(name = "推荐人ID")
    private String referrerId;

    /** 用户状态 */
    @Excel(name = "用户状态")
    private String userStatus;


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
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }
    public void setSvipLevel(String svipLevel) 
    {
        this.svipLevel = svipLevel;
    }

    public String getSvipLevel() 
    {
        return svipLevel;
    }
    public void setTotareward(String totareward) 
    {
        this.totareward = totareward;
    }

    public String getTotareward() 
    {
        return totareward;
    }
    public void setBalance(String balance) 
    {
        this.balance = balance;
    }

    public String getBalance() 
    {
        return balance;
    }
    public void setNonWithdrawableBalance(String nonWithdrawableBalance) 
    {
        this.nonWithdrawableBalance = nonWithdrawableBalance;
    }

    public String getNonWithdrawableBalance() 
    {
        return nonWithdrawableBalance;
    }
    public void setUsdtAddress(String usdtAddress) 
    {
        this.usdtAddress = usdtAddress;
    }

    public String getUsdtAddress() 
    {
        return usdtAddress;
    }
    public void setRegistrationTime(Date registrationTime) 
    {
        this.registrationTime = registrationTime;
    }

    public Date getRegistrationTime() 
    {
        return registrationTime;
    }
    public void setAvatar(String avatar) 
    {
        this.avatar = avatar;
    }

    public String getAvatar() 
    {
        return avatar;
    }
    public void setReferrerId(String referrerId) 
    {
        this.referrerId = referrerId;
    }

    public String getReferrerId() 
    {
        return referrerId;
    }
    public void setUserStatus(String userStatus) 
    {
        this.userStatus = userStatus;
    }

    public String getUserStatus() 
    {
        return userStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("nickname", getNickname())
            .append("svipLevel", getSvipLevel())
            .append("totareward", getTotareward())
            .append("balance", getBalance())
            .append("nonWithdrawableBalance", getNonWithdrawableBalance())
            .append("usdtAddress", getUsdtAddress())
            .append("registrationTime", getRegistrationTime())
            .append("avatar", getAvatar())
            .append("referrerId", getReferrerId())
            .append("userStatus", getUserStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 三级分佣百分比对象 tk_royalty_setting
 *
 * @author ruoyi
 * @date 2024-10-13
 */
public class TkRoyaltySetting extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    @Excel(name = "")
    private Long id;

    /** 一级 */
    @Excel(name = "一级")
    private String first;

    /** 二级 */
    @Excel(name = "二级")
    private String two;

    /** 三级 */
    @Excel(name = "三级")
    private String three;

    /** 奖励 */
    @Excel(name = "奖励")
    private String rewards;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFirst(String first)
    {
        this.first = first;
    }

    public String getFirst()
    {
        return first;
    }
    public void setTwo(String two)
    {
        this.two = two;
    }

    public String getTwo()
    {
        return two;
    }
    public void setThree(String three)
    {
        this.three = three;
    }

    public String getThree()
    {
        return three;
    }
    public void setRewards(String rewards)
    {
        this.rewards = rewards;
    }

    public String getRewards()
    {
        return rewards;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("first", getFirst())
                .append("two", getTwo())
                .append("three", getThree())
                .append("rewards", getRewards())
                .toString();
    }
}

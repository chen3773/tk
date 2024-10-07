package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 文本对象 tk_agreement
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
public class TkAgreement extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 文本名称 */
    @Excel(name = "文本名称")
    private String name;

    /** 文本信息 */
    @Excel(name = "文本信息")
    private String textMessage;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setTextMessage(String textMessage) 
    {
        this.textMessage = textMessage;
    }

    public String getTextMessage() 
    {
        return textMessage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("textMessage", getTextMessage())
            .toString();
    }
}

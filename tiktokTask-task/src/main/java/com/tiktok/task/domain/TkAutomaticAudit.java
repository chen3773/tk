package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 自动审核对象 tk_automatic_audit
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
public class TkAutomaticAudit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long lv0;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long lv1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long lv2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long lv3;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long lv4;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long lv5;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLv0(Long lv0) 
    {
        this.lv0 = lv0;
    }

    public Long getLv0() 
    {
        return lv0;
    }
    public void setLv1(Long lv1) 
    {
        this.lv1 = lv1;
    }

    public Long getLv1() 
    {
        return lv1;
    }
    public void setLv2(Long lv2) 
    {
        this.lv2 = lv2;
    }

    public Long getLv2() 
    {
        return lv2;
    }
    public void setLv3(Long lv3) 
    {
        this.lv3 = lv3;
    }

    public Long getLv3() 
    {
        return lv3;
    }
    public void setLv4(Long lv4) 
    {
        this.lv4 = lv4;
    }

    public Long getLv4() 
    {
        return lv4;
    }
    public void setLv5(Long lv5) 
    {
        this.lv5 = lv5;
    }

    public Long getLv5() 
    {
        return lv5;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("lv0", getLv0())
            .append("lv1", getLv1())
            .append("lv2", getLv2())
            .append("lv3", getLv3())
            .append("lv4", getLv4())
            .append("lv5", getLv5())
            .toString();
    }
}

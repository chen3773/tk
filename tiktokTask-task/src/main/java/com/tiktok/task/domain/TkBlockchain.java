package com.tiktok.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.tiktok.common.annotation.Excel;
import com.tiktok.common.core.domain.BaseEntity;

/**
 * 区块链名称对象 tk_blockchain
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
public class TkBlockchain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String blockchainName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBlockchainName(String blockchainName) 
    {
        this.blockchainName = blockchainName;
    }

    public String getBlockchainName() 
    {
        return blockchainName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("blockchainName", getBlockchainName())
            .toString();
    }
}

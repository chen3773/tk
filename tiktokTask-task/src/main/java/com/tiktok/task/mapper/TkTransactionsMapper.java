package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkTransactions;

/**
 * 上下分日志Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-05
 */
public interface TkTransactionsMapper 
{
    /**
     * 查询上下分日志
     * 
     * @param id 上下分日志主键
     * @return 上下分日志
     */
    public TkTransactions selectTkTransactionsById(Long id);

    /**
     * 查询上下分日志列表
     * 
     * @param tkTransactions 上下分日志
     * @return 上下分日志集合
     */
    public List<TkTransactions> selectTkTransactionsList(TkTransactions tkTransactions);

    /**
     * 新增上下分日志
     * 
     * @param tkTransactions 上下分日志
     * @return 结果
     */
    public int insertTkTransactions(TkTransactions tkTransactions);

    /**
     * 修改上下分日志
     * 
     * @param tkTransactions 上下分日志
     * @return 结果
     */
    public int updateTkTransactions(TkTransactions tkTransactions);

    /**
     * 删除上下分日志
     * 
     * @param id 上下分日志主键
     * @return 结果
     */
    public int deleteTkTransactionsById(Long id);

    /**
     * 批量删除上下分日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkTransactionsByIds(Long[] ids);
}

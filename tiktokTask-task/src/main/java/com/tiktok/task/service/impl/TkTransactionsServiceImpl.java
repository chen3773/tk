package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkTransactionsMapper;
import com.tiktok.task.domain.TkTransactions;
import com.tiktok.task.service.ITkTransactionsService;

/**
 * 上下分日志Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-05
 */
@Service
public class TkTransactionsServiceImpl implements ITkTransactionsService 
{
    @Autowired
    private TkTransactionsMapper tkTransactionsMapper;

    /**
     * 查询上下分日志
     * 
     * @param id 上下分日志主键
     * @return 上下分日志
     */
    @Override
    public TkTransactions selectTkTransactionsById(Long id)
    {
        return tkTransactionsMapper.selectTkTransactionsById(id);
    }

    /**
     * 查询上下分日志列表
     * 
     * @param tkTransactions 上下分日志
     * @return 上下分日志
     */
    @Override
    public List<TkTransactions> selectTkTransactionsList(TkTransactions tkTransactions)
    {
        return tkTransactionsMapper.selectTkTransactionsList(tkTransactions);
    }

    /**
     * 新增上下分日志
     * 
     * @param tkTransactions 上下分日志
     * @return 结果
     */
    @Override
    public int insertTkTransactions(TkTransactions tkTransactions)
    {
        return tkTransactionsMapper.insertTkTransactions(tkTransactions);
    }

    /**
     * 修改上下分日志
     * 
     * @param tkTransactions 上下分日志
     * @return 结果
     */
    @Override
    public int updateTkTransactions(TkTransactions tkTransactions)
    {
        return tkTransactionsMapper.updateTkTransactions(tkTransactions);
    }

    /**
     * 批量删除上下分日志
     * 
     * @param ids 需要删除的上下分日志主键
     * @return 结果
     */
    @Override
    public int deleteTkTransactionsByIds(Long[] ids)
    {
        return tkTransactionsMapper.deleteTkTransactionsByIds(ids);
    }

    /**
     * 删除上下分日志信息
     * 
     * @param id 上下分日志主键
     * @return 结果
     */
    @Override
    public int deleteTkTransactionsById(Long id)
    {
        return tkTransactionsMapper.deleteTkTransactionsById(id);
    }
}

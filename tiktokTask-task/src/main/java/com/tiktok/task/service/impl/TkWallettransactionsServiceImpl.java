package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkWallettransactionsMapper;
import com.tiktok.task.domain.TkWallettransactions;
import com.tiktok.task.service.ITkWallettransactionsService;

/**
 * 用户钱包交易记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-05
 */
@Service
public class TkWallettransactionsServiceImpl implements ITkWallettransactionsService 
{
    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;

    /**
     * 查询用户钱包交易记录
     * 
     * @param id 用户钱包交易记录主键
     * @return 用户钱包交易记录
     */
    @Override
    public TkWallettransactions selectTkWallettransactionsById(Long id)
    {
        return tkWallettransactionsMapper.selectTkWallettransactionsById(id);
    }

    /**
     * 查询用户钱包交易记录列表
     * 
     * @param tkWallettransactions 用户钱包交易记录
     * @return 用户钱包交易记录
     */
    @Override
    public List<TkWallettransactions> selectTkWallettransactionsList(TkWallettransactions tkWallettransactions)
    {
        return tkWallettransactionsMapper.selectTkWallettransactionsList(tkWallettransactions);
    }

    /**
     * 新增用户钱包交易记录
     * 
     * @param tkWallettransactions 用户钱包交易记录
     * @return 结果
     */
    @Override
    public int insertTkWallettransactions(TkWallettransactions tkWallettransactions)
    {
        return tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransactions);
    }

    /**
     * 修改用户钱包交易记录
     * 
     * @param tkWallettransactions 用户钱包交易记录
     * @return 结果
     */
    @Override
    public int updateTkWallettransactions(TkWallettransactions tkWallettransactions)
    {
        return tkWallettransactionsMapper.updateTkWallettransactions(tkWallettransactions);
    }

    /**
     * 批量删除用户钱包交易记录
     * 
     * @param ids 需要删除的用户钱包交易记录主键
     * @return 结果
     */
    @Override
    public int deleteTkWallettransactionsByIds(Long[] ids)
    {
        return tkWallettransactionsMapper.deleteTkWallettransactionsByIds(ids);
    }

    /**
     * 删除用户钱包交易记录信息
     * 
     * @param id 用户钱包交易记录主键
     * @return 结果
     */
    @Override
    public int deleteTkWallettransactionsById(Long id)
    {
        return tkWallettransactionsMapper.deleteTkWallettransactionsById(id);
    }
}

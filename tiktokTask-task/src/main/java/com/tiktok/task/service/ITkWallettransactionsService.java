package com.tiktok.task.service;

import java.util.List;
import com.tiktok.task.domain.TkWallettransactions;

/**
 * 用户钱包交易记录Service接口
 * 
 * @author ruoyi
 * @date 2024-10-05
 */
public interface ITkWallettransactionsService 
{
    /**
     * 查询用户钱包交易记录
     * 
     * @param id 用户钱包交易记录主键
     * @return 用户钱包交易记录
     */
    public TkWallettransactions selectTkWallettransactionsById(Long id);

    /**
     * 查询用户钱包交易记录列表
     * 
     * @param tkWallettransactions 用户钱包交易记录
     * @return 用户钱包交易记录集合
     */
    public List<TkWallettransactions> selectTkWallettransactionsList(TkWallettransactions tkWallettransactions);

    /**
     * 新增用户钱包交易记录
     * 
     * @param tkWallettransactions 用户钱包交易记录
     * @return 结果
     */
    public int insertTkWallettransactions(TkWallettransactions tkWallettransactions);

    /**
     * 修改用户钱包交易记录
     * 
     * @param tkWallettransactions 用户钱包交易记录
     * @return 结果
     */
    public int updateTkWallettransactions(TkWallettransactions tkWallettransactions);

    /**
     * 批量删除用户钱包交易记录
     * 
     * @param ids 需要删除的用户钱包交易记录主键集合
     * @return 结果
     */
    public int deleteTkWallettransactionsByIds(Long[] ids);

    /**
     * 删除用户钱包交易记录信息
     * 
     * @param id 用户钱包交易记录主键
     * @return 结果
     */
    public int deleteTkWallettransactionsById(Long id);
}

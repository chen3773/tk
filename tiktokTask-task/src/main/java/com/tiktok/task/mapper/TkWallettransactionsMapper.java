package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkWallettransactions;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户钱包交易记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-24
 */
@Mapper
public interface TkWallettransactionsMapper
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
     * 删除用户钱包交易记录
     *
     * @param id 用户钱包交易记录主键
     * @return 结果
     */
    public int deleteTkWallettransactionsById(Long id);

    /**
     * 批量删除用户钱包交易记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkWallettransactionsByIds(Long[] ids);
}

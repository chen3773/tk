package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkWithdrawals;

/**
 * 提现记录Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-26
 */
public interface TkWithdrawalsMapper 
{
    /**
     * 查询提现记录
     * 
     * @param id 提现记录主键
     * @return 提现记录
     */
    public TkWithdrawals selectTkWithdrawalsById(Long id);

    /**
     * 查询提现记录列表
     * 
     * @param tkWithdrawals 提现记录
     * @return 提现记录集合
     */
    public List<TkWithdrawals> selectTkWithdrawalsList(TkWithdrawals tkWithdrawals);

    /**
     * 新增提现记录
     * 
     * @param tkWithdrawals 提现记录
     * @return 结果
     */
    public int insertTkWithdrawals(TkWithdrawals tkWithdrawals);

    /**
     * 修改提现记录
     * 
     * @param tkWithdrawals 提现记录
     * @return 结果
     */
    public int updateTkWithdrawals(TkWithdrawals tkWithdrawals);

    /**
     * 删除提现记录
     * 
     * @param id 提现记录主键
     * @return 结果
     */
    public int deleteTkWithdrawalsById(Long id);

    /**
     * 批量删除提现记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkWithdrawalsByIds(Long[] ids);
}

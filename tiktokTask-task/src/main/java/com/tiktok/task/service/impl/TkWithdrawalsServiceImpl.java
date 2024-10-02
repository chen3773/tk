package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkWithdrawalsMapper;
import com.tiktok.task.domain.TkWithdrawals;
import com.tiktok.task.service.ITkWithdrawalsService;

/**
 * 提现记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-26
 */
@Service
public class TkWithdrawalsServiceImpl implements ITkWithdrawalsService 
{
    @Autowired
    private TkWithdrawalsMapper tkWithdrawalsMapper;

    /**
     * 查询提现记录
     * 
     * @param id 提现记录主键
     * @return 提现记录
     */
    @Override
    public TkWithdrawals selectTkWithdrawalsById(Long id)
    {
        return tkWithdrawalsMapper.selectTkWithdrawalsById(id);
    }

    /**
     * 查询提现记录列表
     * 
     * @param tkWithdrawals 提现记录
     * @return 提现记录
     */
    @Override
    public List<TkWithdrawals> selectTkWithdrawalsList(TkWithdrawals tkWithdrawals)
    {
        return tkWithdrawalsMapper.selectTkWithdrawalsList(tkWithdrawals);
    }

    /**
     * 新增提现记录
     * 
     * @param tkWithdrawals 提现记录
     * @return 结果
     */
    @Override
    public int insertTkWithdrawals(TkWithdrawals tkWithdrawals)
    {
        return tkWithdrawalsMapper.insertTkWithdrawals(tkWithdrawals);
    }

    /**
     * 修改提现记录
     * 
     * @param tkWithdrawals 提现记录
     * @return 结果
     */
    @Override
    public int updateTkWithdrawals(TkWithdrawals tkWithdrawals)
    {
        return tkWithdrawalsMapper.updateTkWithdrawals(tkWithdrawals);
    }

    /**
     * 批量删除提现记录
     * 
     * @param ids 需要删除的提现记录主键
     * @return 结果
     */
    @Override
    public int deleteTkWithdrawalsByIds(Long[] ids)
    {
        return tkWithdrawalsMapper.deleteTkWithdrawalsByIds(ids);
    }

    /**
     * 删除提现记录信息
     * 
     * @param id 提现记录主键
     * @return 结果
     */
    @Override
    public int deleteTkWithdrawalsById(Long id)
    {
        return tkWithdrawalsMapper.deleteTkWithdrawalsById(id);
    }
}

package com.tiktok.task.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tiktok.task.domain.TkUsers;
import com.tiktok.task.domain.TkWallettransactions;
import com.tiktok.task.mapper.TkUsersMapper;
import com.tiktok.task.mapper.TkWallettransactionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkWithdrawalsMapper;
import com.tiktok.task.domain.TkWithdrawals;
import com.tiktok.task.service.ITkWithdrawalsService;
import org.springframework.util.Assert;

/**
 * 提现记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-03
 */
@Service
public class TkWithdrawalsServiceImpl implements ITkWithdrawalsService 
{
    @Autowired
    private TkWithdrawalsMapper tkWithdrawalsMapper;
    @Autowired
    private TkUsersMapper tkUsersMapper;

    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;

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
        TkWithdrawals tkWithdrawals1 = new TkWithdrawals();
        tkWithdrawals1.setId(tkWithdrawals.getId());

        TkWithdrawals tkWithdrawals2 = tkWithdrawalsMapper.selectTkWithdrawalsList(tkWithdrawals1).get(0);

        Assert.isTrue(tkWithdrawals2!=null,"数据错误");

        Assert.isTrue(tkWithdrawals2.getStatus().toString().equals("0"),"状态不允许操作");
        if(tkWithdrawals.getStatus().toString().equals("2")){
            //返还余额
            Long uid = tkWithdrawals.getUid();
            TkUsers tkUsers = tkUsersMapper.selectTkUsersByUid(uid);
            String balance = new BigDecimal(tkUsers.getBalance()).add(tkWithdrawals.getAmount()).toString();
            tkUsers.setBalance(balance);
            tkUsersMapper.updateTkUsers(tkUsers);

            //添加记录
            //添加记录
            TkWallettransactions tkWallettransaction = new TkWallettransactions();
            tkWallettransaction.setUserid(uid);
            tkWallettransaction.setFundBalance(new BigDecimal(balance));
            tkWallettransaction.setCategory("income");
            tkWallettransaction.setDescription("Withdrawal rejected");
            tkWallettransaction.setAmount(tkWithdrawals.getAmount());
            tkWallettransaction.setTransactionStatus("已完成");
            tkWallettransaction.setUpdatedAt(new Date());
            tkWallettransaction.setTransactionType("income");
            tkWallettransaction.setTransactionDate(new Date());
            tkWallettransaction.setOrderNumber("");
            tkWallettransaction.setWithdraw("yes");
            Assert.isTrue(tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransaction)!=0,"网络异常...");
            //修改提现的状态
        }
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

package com.tiktok.task.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.task.domain.*;
import com.tiktok.task.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.service.ITkTaskAcceptancesService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import sun.dc.pr.PRError;

/**
 * 用户接取任务Service业务层处理
 *
 * @author ruoyi
 * @date 2024-09-25
 */
@Service
public class TkTaskAcceptancesServiceImpl implements ITkTaskAcceptancesService
{
    @Autowired
    private TkTaskAcceptancesMapper tkTaskAcceptancesMapper;
    @Autowired
    private TkRoyaltySettingMapper tkRoyaltySettingMapper;
    @Autowired
    private TkInvitationMapper tkInvitationMapper;
    @Autowired
    private TkTasksMapper tkTasksMapper;
    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;
    @Autowired
    private TkUsersMapper tkUsersMapper;

    /**
     * 查询用户接取任务
     *
     * @param id 用户接取任务主键
     * @return 用户接取任务
     */
    @Override
    public TkTaskAcceptances selectTkTaskAcceptancesById(Long id)
    {
        return tkTaskAcceptancesMapper.selectTkTaskAcceptancesById(id);
    }

    /**
     * 查询用户接取任务列表
     *
     * @param tkTaskAcceptances 用户接取任务
     * @return 用户接取任务
     */
    @Override
    public List<TkTaskAcceptances> selectTkTaskAcceptancesList(TkTaskAcceptances tkTaskAcceptances)
    {
        return tkTaskAcceptancesMapper.selectTkTaskAcceptancesList(tkTaskAcceptances);
    }

    /**
     * 新增用户接取任务
     *
     * @param tkTaskAcceptances 用户接取任务
     * @return 结果
     */
    @Override
    public int insertTkTaskAcceptances(TkTaskAcceptances tkTaskAcceptances)
    {
        return tkTaskAcceptancesMapper.insertTkTaskAcceptances(tkTaskAcceptances);
    }

    /**
     * 修改用户接取任务
     *
     * @param tkTaskAcceptances 用户接取任务
     * @return 结果
     */
    @Override
    public int updateTkTaskAcceptances(TkTaskAcceptances tkTaskAcceptances)
    {
        return tkTaskAcceptancesMapper.updateTkTaskAcceptances(tkTaskAcceptances);
    }

    /**
     * 批量删除用户接取任务
     *
     * @param ids 需要删除的用户接取任务主键
     * @return 结果
     */
    @Override
    public int deleteTkTaskAcceptancesByIds(Long[] ids)
    {
        return tkTaskAcceptancesMapper.deleteTkTaskAcceptancesByIds(ids);
    }

    /**
     * 删除用户接取任务信息
     *
     * @param id 用户接取任务主键
     * @return 结果
     */
    @Override
    public int deleteTkTaskAcceptancesById(Long id)
    {
        return tkTaskAcceptancesMapper.deleteTkTaskAcceptancesById(id);
    }

    @Override
    @Transactional
    public AjaxResult TaskAudit(Long id, boolean pass) {
        Assert.isTrue(id!=null,"参数缺失");
        TkTaskAcceptances tkTaskAcceptances = tkTaskAcceptancesMapper.selectTkTaskAcceptancesById(id);
        Assert.isTrue(tkTaskAcceptances!=null,"审核任务不存在");
        TkTasks tkTasks = tkTasksMapper.selectTkTasksById(tkTaskAcceptances.getTaskId());
        if(pass){
            tkTaskAcceptances.setStatus("2");
            tkTaskAcceptances.setApprovalTime(new Date());

            TkUsers tkUsers1 = tkUsersMapper.selectTkUsersByUid(tkTaskAcceptances.getUid());
            //给用户添加余额
            String balance = new BigDecimal(tkUsers1.getBalance()).add(tkTasks.getRewardAmount()).toString();
            tkUsers1.setBalance(balance);
            tkUsers1.setTotareward(new BigDecimal(tkUsers1.getTotareward()).add(tkTasks.getRewardAmount()).toString());
            Assert.isTrue(tkUsersMapper.updateTkUsers(tkUsers1)!=0,"网络异常...");
            //添加用户钱包记录
            //添加记录
            TkWallettransactions tkWallettransactions2 = new TkWallettransactions();
            tkWallettransactions2.setUserid(tkUsers1.getUid());
            tkWallettransactions2.setFundBalance(new BigDecimal(balance));
            tkWallettransactions2.setCategory("income");
            tkWallettransactions2.setDescription("#"+tkTasks.getId()+"Task reward");
            tkWallettransactions2.setAmount(tkTasks.getRewardAmount());
            tkWallettransactions2.setTransactionStatus("已完成");
            tkWallettransactions2.setUpdatedAt(new Date());
            tkWallettransactions2.setTransactionType("income");
            tkWallettransactions2.setTransactionDate(new Date());
            Assert.isTrue(tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransactions2)!=0,"网络异常...");

            //开始分佣
            TkRoyaltySetting tkRoyaltySetting = tkRoyaltySettingMapper.selectTkRoyaltySettingById(1L);//分佣比例
            //获取出上级邀请人
            TkInvitation tkInvitation = new TkInvitation();
            tkInvitation.setInviteeId(tkTaskAcceptances.getUid());
            List<TkInvitation> tkInvitations = tkInvitationMapper.selectTkInvitationList(tkInvitation);
            for (int i = 0; i < tkInvitations.size(); i++) {
                Long inviterId = tkInvitations.get(i).getInviterId();//上级id
                Long id2 = tkInvitations.get(i).getId();
                Long level = tkInvitations.get(i).getLevel();
                BigDecimal rewardAmount = tkTasks.getRewardAmount(); // 假设这个方法返回一个 BigDecimal
                String royalty = "";

                // 根据 level 获取 royalty
                if (level == 1) {
                    royalty = tkRoyaltySetting.getFirst();
                } else if (level == 2) {
                    royalty = tkRoyaltySetting.getTwo();
                } else if (level == 3) {
                    royalty = tkRoyaltySetting.getThree();
                }
                // 将 royalty 转换为 BigDecimal
                BigDecimal royaltyAmount = new BigDecimal(royalty);
                // 计算 rewardAmount * royaltyAmount / 100    添加佣金记录
                BigDecimal result = rewardAmount.multiply(royaltyAmount).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);

                //给用户添加余额
                TkUsers tkUsers = tkUsersMapper.selectTkUsersByUid(inviterId);
                BigDecimal add = new BigDecimal(tkUsers.getBalance()).add(result);
                tkUsers.setBalance(add.toString());
                Assert.isTrue(tkUsersMapper.updateTkUsers(tkUsers)!=0,"网络异常...");

                //添加记录
                TkWallettransactions tkWallettransactions = new TkWallettransactions();
                tkWallettransactions.setUserid(inviterId);
                tkWallettransactions.setFundBalance(add);
                tkWallettransactions.setCategory("income");
                tkWallettransactions.setDescription("#"+tkTasks.getId()+"Subordinate reward");
                tkWallettransactions.setAmount(result);
                tkWallettransactions.setTransactionStatus("已完成");
                tkWallettransactions.setUpdatedAt(new Date());
                tkWallettransactions.setTransactionType("income");
                tkWallettransactions.setTransactionDate(new Date());
                Assert.isTrue(tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransactions)!=0,"网络异常...");
                //更新邀请关系表金额
                tkInvitations.get(i).setAmount(new BigDecimal(tkInvitations.get(i).getAmount()).add(result).toString());
                tkInvitationMapper.updateTkInvitation(tkInvitations.get(i));
            }

        }else {
            tkTaskAcceptances.setStatus("3");
            tkTaskAcceptances.setApprovalTime(new Date());
        }

        Assert.isTrue(tkTaskAcceptancesMapper.updateTkTaskAcceptances(tkTaskAcceptances)!=0,"网络出现异常...");
       return  AjaxResult.success("审核完成");
    }
}

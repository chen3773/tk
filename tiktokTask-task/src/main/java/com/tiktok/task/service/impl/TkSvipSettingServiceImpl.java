package com.tiktok.task.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import com.tiktok.task.domain.*;
import com.tiktok.task.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.service.ITkSvipSettingService;
import org.springframework.util.Assert;

/**
 * svip默认配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@Service
public class TkSvipSettingServiceImpl implements ITkSvipSettingService 
{
    @Autowired
    private TkSvipSettingMapper tkSvipSettingMapper;
    @Autowired
    private TkUsersMapper tkUsersMapper;
    @Autowired
    private TkRoyaltySettingMapper tkRoyaltySettingMapper;
    @Autowired
    private TkInvitationMapper tkInvitationMapper;
    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;
    @Autowired
    private TkTasknumMapper tkTasknumMapper;

    /**
     * 查询svip默认配置
     * 
     * @param id svip默认配置主键
     * @return svip默认配置
     */
    @Override
    public TkSvipSetting selectTkSvipSettingById(Long id)
    {
        return tkSvipSettingMapper.selectTkSvipSettingById(id);
    }

    /**
     * 查询svip默认配置列表
     * 
     * @param tkSvipSetting svip默认配置
     * @return svip默认配置
     */
    @Override
    public List<TkSvipSetting> selectTkSvipSettingList(TkSvipSetting tkSvipSetting)
    {
        return tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting);
    }

    /**
     * 新增svip默认配置
     * 
     * @param tkSvipSetting svip默认配置
     * @return 结果
     */
    @Override
    public int insertTkSvipSetting(TkSvipSetting tkSvipSetting)
    {
        return tkSvipSettingMapper.insertTkSvipSetting(tkSvipSetting);
    }

    /**
     * 修改svip默认配置
     * 
     * @param tkSvipSetting svip默认配置
     * @return 结果
     */
    @Override
    public int updateTkSvipSetting(TkSvipSetting tkSvipSetting)
    {
        return tkSvipSettingMapper.updateTkSvipSetting(tkSvipSetting);
    }

    /**
     * 批量删除svip默认配置
     * 
     * @param ids 需要删除的svip默认配置主键
     * @return 结果
     */
    @Override
    public int deleteTkSvipSettingByIds(Long[] ids)
    {
        return tkSvipSettingMapper.deleteTkSvipSettingByIds(ids);
    }

    /**
     * 删除svip默认配置信息
     * 
     * @param id svip默认配置主键
     * @return 结果
     */
    @Override
    public int deleteTkSvipSettingById(Long id)
    {
        return tkSvipSettingMapper.deleteTkSvipSettingById(id);
    }

    @Override
    public int UpgradeSvip(Long uid, Long lv) {
        //获取出默认配置
        TkSvipSetting tkSvipSetting = new TkSvipSetting();
        tkSvipSetting.setVipLevel(lv);
        List<TkSvipSetting> tkSvipSettings = tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting);

        //更改用户等级
        TkUsers tkuser = tkUsersMapper.selectTkUsersByUid(uid);
        tkuser.setSvipLevel(lv);
        tkUsersMapper.updateTkUsers(tkuser);


        //清空用户配额  重新分配
        TkTasknum tkTasknum = new TkTasknum();
        tkTasknum.setUserId(uid);
        TkTasknum tkTasknum1 = tkTasknumMapper.selectTkTasknumList(tkTasknum).get(0);
        tkTasknum1.setExperienceTaskCount(0L);
        tkTasknum1.setNormalTaskCount(tkSvipSettings.get(0).getDailyTaskCount());
        tkTasknumMapper.updateTkTasknum(tkTasknum1);
        //分成
        //开始分佣
        TkRoyaltySetting tkRoyaltySetting = tkRoyaltySettingMapper.selectTkRoyaltySettingById(1L);//分佣比例
        //获取出上级邀请人
        TkInvitation tkInvitation = new TkInvitation();
        tkInvitation.setInviteeId(uid);
        List<TkInvitation> tkInvitations = tkInvitationMapper.selectTkInvitationList(tkInvitation);
        for (int i = 0; i < tkInvitations.size(); i++) {
            Long inviterId = tkInvitations.get(i).getInviterId();//上级id
            Long id2 = tkInvitations.get(i).getId();
            Long level = tkInvitations.get(i).getLevel();
            BigDecimal rewardAmount = tkSvipSettings.get(0).getUpgradeAmount(); // 假设这个方法返回一个 BigDecimal
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
            tkWallettransactions.setDescription("#"+uid+"Upgrade bonus");
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
        return 1;
    }
}

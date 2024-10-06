package com.tiktok.task.service.impl;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.common.utils.SecurityUtils;
import com.tiktok.task.domain.TkUserDefault;
import com.tiktok.task.domain.TkUsers;
import com.tiktok.task.domain.TkWallettransactions;
import com.tiktok.task.mapper.AdminMapper;
import com.tiktok.task.mapper.TkUserDefaultMapper;
import com.tiktok.task.mapper.TkUsersMapper;
import com.tiktok.task.mapper.TkWallettransactionsMapper;
import com.tiktok.task.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private TkUsersMapper tkUsersMapper;
    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;

    @Override
    public AjaxResult HomePage() {
        Long userId = SecurityUtils.getUserId();

        return AjaxResult.success(adminMapper.HomePage(userId));
    }

    @Override
    public AjaxResult AddAndDeduct(String amount,String withdraw,String add,String uid) {
        TkUsers tkUsers = tkUsersMapper.selectTkUsersByUid(Long.valueOf(uid));
        String str = "top-up:";

        BigDecimal bigDecimal = new BigDecimal(amount);
        if(withdraw.equals("yes")){
            if(add.equals("yes")){
                tkUsers.setBalance(new BigDecimal(tkUsers.getBalance())
                        .add(bigDecimal).toString());
            }else {
                tkUsers.setBalance(new BigDecimal(tkUsers.getBalance())
                        .subtract(bigDecimal).toString());
            }
        }else if(withdraw.equals("no")){
            if(add.equals("yes")){
                tkUsers.setBalance(new BigDecimal(tkUsers.getBalance())
                        .add(bigDecimal).toString());
                tkUsers.setNonWithdrawableBalance(new BigDecimal(tkUsers.getNonWithdrawableBalance())
                        .add(bigDecimal).toString());
            }else {
                tkUsers.setBalance(new BigDecimal(tkUsers.getBalance())
                        .subtract(bigDecimal).toString());
                tkUsers.setNonWithdrawableBalance(new BigDecimal(tkUsers.getNonWithdrawableBalance())
                        .subtract(bigDecimal).toString());
            }

        }
        int i = tkUsersMapper.updateTkUsers(tkUsers);

        if(add.equals("no")){
            bigDecimal = new BigDecimal(amount).negate();
            str = "Deduct money:";
        }
        //添加记录
        TkWallettransactions tkWallettransactions = new TkWallettransactions();
        tkWallettransactions.setUserid(Long.valueOf(uid));
        tkWallettransactions.setTransactionType(str);
        tkWallettransactions.setAmount(bigDecimal);
        tkWallettransactions.setTransactionDate(new Date());
        tkWallettransactions.setFundBalance(new BigDecimal(tkUsers.getBalance()));
        tkWallettransactions.setDescription(str+amount);
        tkWallettransactions.setCategory("income");
        tkWallettransactions.setTransactionStatus("已完成");
        tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransactions);
        return AjaxResult.success(tkUsers);
    }
}

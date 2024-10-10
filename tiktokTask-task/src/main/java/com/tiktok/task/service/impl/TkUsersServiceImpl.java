package com.tiktok.task.service.impl;

import java.beans.Transient;
import java.math.BigDecimal;
import java.security.Security;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.common.core.domain.entity.SysUser;
import com.tiktok.common.utils.DateUtils;
import com.tiktok.common.utils.SecurityUtils;
import com.tiktok.common.utils.StringUtils;
import com.tiktok.system.service.ISysDeptService;
import com.tiktok.system.service.ISysPostService;
import com.tiktok.system.service.ISysRoleService;
import com.tiktok.system.service.ISysUserService;
import com.tiktok.task.domain.*;
import com.tiktok.task.mapper.*;
import com.tiktok.task.util.InviteCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.service.ITkUsersService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import static com.tiktok.common.core.domain.AjaxResult.error;
import static com.tiktok.common.utils.SecurityUtils.getUsername;
import static com.tiktok.task.util.SVIPNotificationGenerator.generateRandomMessages;

/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-20
 */
@Service
public class TkUsersServiceImpl implements ITkUsersService 
{
    @Autowired
    private TkUsersMapper tkUsersMapper;

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private TkUserDefaultMapper tkUserDefaultMapper;

    @Autowired
    private TkInvitationMapper tkInvitationMapper;
    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;

    @Autowired
    private TkWithdrawalsMapper tkWithdrawalsMapper;

    @Autowired
    private TkTasknumMapper tkTasknumMapper;
    @Autowired
    private TkSpecialTaskMapper tkSpecialTaskMapper;
    @Autowired
    private TkTasksMapper tkTasksMapper;

    @Autowired
    private TkTaskAcceptancesMapper tkTaskAcceptancesMapper;




    /**
     * 查询用户信息
     * 
     * @param uid 用户信息主键
     * @return 用户信息
     */
    @Override
    public TkUsers selectTkUsersByUid(Long uid)
    {
        return tkUsersMapper.selectTkUsersByUid(uid);
    }

    /**
     * 查询用户信息列表
     * 
     * @param tkUsers 用户信息
     * @return 用户信息
     */
    @Override
    public List<TkUsers> selectTkUsersList(TkUsers tkUsers)
    {

        List<TkUsers> tkUsers1 = tkUsersMapper.selectTkUsersList(tkUsers);
        return tkUsers1;
    }

    /**
     * 新增用户信息
     * 
     * @param tkUsers 用户信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTkUsers(TkUsers tkUsers)
    {
        //判断用户名是否存在
        TkUsers tkUsers3 = new TkUsers();
        tkUsers3.setUsername(tkUsers.getUsername());
        Assert.isTrue(tkUsersMapper.selectTkUsersList(tkUsers3).size()==0,"用户名已存在");

        String InviteCode = InviteCodeGenerator.generateInviteCode();//邀请码


        //todo 判断邀请码是否存在
        TkUsers tkUsers1 = new TkUsers();
        tkUsers1.setInvitationCode(tkUsers.getInvitationCode());
        List<TkUsers> tkUsers4 = tkUsersMapper.selectTkUsersList(tkUsers1);
        Assert.isTrue(tkUsers4.size()!=0,"邀请码不存在");
        tkUsers.setReferrerId(tkUsers4.get(0).getUid());

        Long userId = SecurityUtils.getUserId();

        tkUsers.setCreateTime(DateUtils.getNowDate());
        //设置创建人
        tkUsers.setCreateBy(userId.toString());
        tkUsers.setAvatar("/profile/upload/avatar/2024/09/27/avatar_20240927193831A001.png");
        tkUsers.setTotareward("0");
        tkUsers.setRegistrationTime(new Date());//注册时间
        tkUsers.setWithdraw("0");
        tkUsers.setUserStatus("1");
        tkUsers.setBalance("0");
        tkUsers.setNickname("Default");
        tkUsers.setSvipLevel(0L);
        tkUsers.setNonWithdrawableBalance("0");
        tkUsers.setInvitationCode(InviteCode);

        SysUser user = new SysUser();
        user.setDeptId(110L);
        user.setRoleId(104L);
        user.setPassword(tkUsers.getPassword());
        user.setUserName(tkUsers.getUsername());
        user.setNickName(tkUsers.getNickname());
        deptService.checkDeptDataScope(110L);//分配岗位
        roleService.checkRoleDataScope(104L);//分配角色

        if (!userService.checkUserNameUnique(user))
        {
            System.out.println(error("新增用户'" + user.getUserName() + "'失败，登录账号已存在"));
        }
        else if (StringUtils.isNotEmpty(user.getPhonenumber()) && !userService.checkPhoneUnique(user))
        {
            System.out.println(error("新增用户'" + user.getUserName() + "'失败，手机号码已存在"));
        }
        else if (StringUtils.isNotEmpty(user.getEmail()) && !userService.checkEmailUnique(user))
        {
            System.out.println(error("新增用户'" + user.getUserName() + "'失败，邮箱账号已存在"));
        }
        user.setCreateBy(getUsername());
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        Assert.isTrue( userService.insertUser(user)>0,"新增用户失败");
        int i = tkUsersMapper.insertTkUsers(tkUsers);
        Long uid = tkUsers.getUid();
        //添加一级
        TkInvitation tkInvitation = new TkInvitation();
        tkInvitation.setInviteeId(uid);
        tkInvitation.setInviterId(tkUsers.getReferrerId());
        tkInvitation.setAmount("0.00");
        tkInvitation.setLevel(1L);
        Assert.isTrue(tkInvitationMapper.insertTkInvitation(tkInvitation)>0,"新增用户失败");

        //添加二级
        if(tkUsers4.get(0).getReferrerId()!=null){
            tkInvitation.setInviterId(tkUsers4.get(0).getReferrerId());
            tkInvitation.setLevel(2L);
            Assert.isTrue( tkInvitationMapper.insertTkInvitation(tkInvitation)>0,"新增用户失败");

            TkUsers tkUsers2 = tkUsersMapper.selectTkUsersByUid(tkUsers4.get(0).getReferrerId());
            if(tkUsers2.getReferrerId()!=null){//添加三级
                tkInvitation.setInviterId(tkUsers2.getReferrerId());
                tkInvitation.setLevel(3L);
                Assert.isTrue( tkInvitationMapper.insertTkInvitation(tkInvitation)>0,"新增用户失败");
            }
        }

        //导入用户默认配置
        TkUserDefault tkUserDefault = tkUserDefaultMapper.selectTkUserDefaultById(1L);

        TkTasknum tkTasknum = new TkTasknum();
        tkTasknum.setUserId(uid);
        tkTasknum.setExperienceTaskCount(tkUserDefault.getExperienceTaskCount());
        tkTasknum.setNormalTaskCount(tkUserDefault.getRegularTaskCount());
        tkTasknum.setHiddenTaskCount(tkUserDefault.getRegularTaskCount());
        tkTasknum.setTotalCompletedTasks(0L);
        //创建用户限制
        tkTasknumMapper.insertTkTasknum(tkTasknum);
        //去创建系统用户
        return 1;
    }

    /**
     * 修改用户信息
     * 
     * @param tkUsers 用户信息
     * @return 结果
     */
    @Override
    public int updateTkUsers(TkUsers tkUsers)
    {
        tkUsers.setUpdateTime(DateUtils.getNowDate());
        return tkUsersMapper.updateTkUsers(tkUsers);
    }

    /**
     * 批量删除用户信息
     * 
     * @param uids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTkUsersByUids(Long[] uids)
    {
        return tkUsersMapper.deleteTkUsersByUids(uids);
    }

    /**
     * 删除用户信息信息
     * 
     * @param uid 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTkUsersByUid(Long uid)
    {
        return tkUsersMapper.deleteTkUsersByUid(uid);
    }

    @Override
    public List<TkWallettransactions> getWalletRecords(String category) {
        TkWallettransactions tkWallettransactions = new TkWallettransactions();
        tkWallettransactions.setUserid(SecurityUtils.getLoginUser().getUser().getUid());
        tkWallettransactions.setCategory(category);
        return tkWallettransactionsMapper.selectTkWallettransactionsList(tkWallettransactions);
    }

    @Override
    public AjaxResult updateUser(TkUsers tkUsers) {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();

        TkUsers tkUsers1 = new TkUsers();
        tkUsers1.setUid(uid);
        tkUsers1.setNickname(tkUsers.getNickname());
        tkUsers1.setAvatar(tkUsers.getAvatar());
        tkUsers1.setUsdtAddress(tkUsers.getUsdtAddress());
        tkUsers1.setBlockchainName(tkUsers.getBlockchainName());
        int i = tkUsersMapper.updateTkUsers(tkUsers1);
        Assert.isTrue(i!=0,"error");
        return AjaxResult.success("Successful");
    }


    @Override
    @Transactional
    public AjaxResult withdraw(String amount) {

        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        //判断是否有未完成任务
        TkTaskAcceptances tkTaskAcceptances = new TkTaskAcceptances();
        tkTaskAcceptances.setUid(uid);
        List<TkTaskAcceptances> tkTaskAcceptances1 = tkTaskAcceptancesMapper.selectTkTaskAcceptancesList(tkTaskAcceptances);
        for (int i = 0; i < tkTaskAcceptances1.size(); i++) {
            Assert.isTrue(!tkTaskAcceptances1.get(i).getStatus().equals("0"),"Please complete all tasks before withdrawing");
        }

        // 将字符串转换为 double
        double value = Double.parseDouble(amount);
        Assert.isTrue(value>0,"error");

        TkUserDefault tkUserDefault = tkUserDefaultMapper.selectTkUserDefaultById(1L);
        Assert.isTrue(Double.parseDouble(tkUserDefault.getMinimumWithdrawalAmount())<=value,"Minimum withdrawal ："+ tkUserDefault.getMinimumWithdrawalAmount());


        TkUsers tkUsers = tkUsersMapper.selectTkUsersByUid(uid);
        Assert.isTrue(tkUsers.getBlockchainName()!=null,"Please bind your wallet first");

        Assert.isTrue(tkUsers.getWithdraw().equals("0"),"Withdrawal exception, contact customer service");

        // 获取余额和不可提现金额
        String balanceStr = tkUsers.getBalance(); // 余额
        String nonWithdrawableBalanceStr = tkUsers.getNonWithdrawableBalance(); // 不可提现金额
        // 将字符串转换为 BigDecimal
        BigDecimal balance = new BigDecimal(balanceStr);
        BigDecimal nonWithdrawableBalance = new BigDecimal(nonWithdrawableBalanceStr);
        // 计算可提现余额
        BigDecimal withdrawableBalance = balance.subtract(nonWithdrawableBalance);
        // 将 amount 转换为 BigDecimal
        BigDecimal amountToWithdraw = new BigDecimal(amount);
        // 检查余额是否足够
       Assert.isTrue(withdrawableBalance.compareTo(amountToWithdraw) >= 0,"error");

        // 计算提现后的余额
        BigDecimal newBalance = balance.subtract(amountToWithdraw);

        // 更新用户余额
        tkUsers.setBalance(newBalance.toString());
        tkUsersMapper.updateTkUsers(tkUsers);

        TkWithdrawals tkWithdrawals = new TkWithdrawals();
        tkWithdrawals.setAmount(new BigDecimal(amount));
        tkWithdrawals.setUsername(tkUsers.getUsername());
        tkWithdrawals.setUid(uid);
        tkWithdrawals.setBlockchainName(tkUsers.getBlockchainName());
        tkWithdrawals.setAddress(tkUsers.getUsdtAddress());
        tkWithdrawals.setWithdrawalTime(new Date());
        tkWithdrawals.setStatus(0L);
        tkWithdrawalsMapper.insertTkWithdrawals(tkWithdrawals);
        //钱包记录
        TkWallettransactions tkWallettransactions = new TkWallettransactions();
        tkWallettransactions.setAmount(new BigDecimal(amount).negate());
        tkWallettransactions.setUserid(uid);
        tkWallettransactions.setTransactionType("withdraw");
        tkWallettransactions.setTransactionDate(new Date());
        tkWallettransactions.setFundBalance(newBalance);
        tkWallettransactions.setCategory("withdraw");
        tkWallettransactions.setTransactionStatus("待处理");
        tkWallettransactions.setDescription("withdraw："+new BigDecimal(amount));
        tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransactions);

        // 返回新的余额
        return AjaxResult.success("Successful");
    }

    @Override
    public AjaxResult getUserUpgradeVIPMessage() {
        List<String> notifications = generateRandomMessages(5); // 生成 5 条消息
        return AjaxResult.success(notifications);
    }

    @Override
    public AjaxResult GetFundComposition() {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        //赚取的资金
        TkUsers tkUsers = tkUsersMapper.selectTkUsersByUid(uid);

        //获取一级返佣数额
        TkInvitation tkInvitation = new TkInvitation();
        tkInvitation.setInviterId(uid);
        tkInvitation.setLevel(1L);
        List<TkInvitation> tkInvitations = tkInvitationMapper.selectTkInvitationList(tkInvitation);
        double  customerRebate = 0;
        if(tkInvitations.size()!=0){
            for (int i = 0; i < customerRebate; i++) {
                customerRebate = customerRebate + Double.parseDouble(tkInvitations.get(i).getAmount());
            }
        }

        tkInvitation.setLevel(2L);
        tkInvitations = tkInvitationMapper.selectTkInvitationList(tkInvitation);
        double commissionRebate = 0;
        if(tkInvitations.size()!=0){
            for (int i = 0; i < customerRebate; i++) {
                commissionRebate = commissionRebate + Double.parseDouble(tkInvitations.get(i).getAmount());
            }
        }

        tkInvitation.setLevel(3L);
        tkInvitations = tkInvitationMapper.selectTkInvitationList(tkInvitation);
        if(tkInvitations.size()!=0){
            for (int i = 0; i < customerRebate; i++) {
                commissionRebate = commissionRebate + Double.parseDouble(tkInvitations.get(i).getAmount());
            }
        }

                

        HashMap<String, Object> res = new HashMap<>();
        res.put("JobCommission",tkUsers.getTotareward());
        res.put("customerRebate",customerRebate);
        res.put("commissionRebate",commissionRebate);
        res.put("ShareholderDividends","0");

        return AjaxResult.success(res);
    }

    @Override
    @Transactional
    public AjaxResult addSpecialTask(TaskData taskData) {
        for (int i = 0; i < taskData.getUids().size(); i++) {
            //过滤
            TkSpecialTask tkSpecialTask1 = new TkSpecialTask();
            tkSpecialTask1.setUserId(Long.valueOf(taskData.getUids().get(i)));

            List<TkSpecialTask> tkSpecialTasks = tkSpecialTaskMapper.selectTkSpecialTaskList(tkSpecialTask1);
            Long[] idArray = tkSpecialTasks.stream()
                    .map(TkSpecialTask::getId)
                    .toArray(Long[]::new);
            if(idArray.length!=0){
                int i2 = tkSpecialTaskMapper.deleteTkSpecialTaskByIds(idArray);

            }
            for (int i1 = 0; i1 < taskData.getTaskList().size(); i1++) {
                String taskId = taskData.getTaskList().get(i1).getTaskId();
               Assert.isTrue( tkTasksMapper.selectTkTasksById(Long.valueOf(taskId))!=null,
                       "这个任务id不存在-->"+taskId);

                TkSpecialTask tkSpecialTask = new TkSpecialTask();
                tkSpecialTask.setUserId(Long.valueOf(taskData.getUids().get(i)));
                tkSpecialTask.setTaskId(Long.valueOf(taskId));
                tkSpecialTask.setStatus("0");
                if(taskData.getTaskList().get(i1).getCount().equals("0")){
                    tkSpecialTask.setStatus("1");
                }

                tkSpecialTask.setTriggerCount(Long.valueOf(taskData.getTaskList().get(i1).getCount()));
                tkSpecialTaskMapper.insertTkSpecialTask(tkSpecialTask);
            }
        }
        return AjaxResult.success("添加成功");
    }
}

package com.tiktok.task.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.common.utils.SecurityUtils;
import com.tiktok.task.domain.*;
import com.tiktok.task.domain.ov.UserTaskOV;
import com.tiktok.task.mapper.*;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.service.ITkTasksService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * 任务列Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@Service
public class TkTasksServiceImpl implements ITkTasksService 
{
    @Autowired
    private TkTasksMapper tkTasksMapper;
    
    @Autowired
    private TkUsersMapper tkUsersMapper;
    @Autowired
    private TkTaskAcceptancesMapper tkTaskAcceptancesMapper;
    @Autowired
    private TkTasknumMapper tkTasknumMapper;
    @Autowired
    private TkSpecialTaskMapper tkSpecialTaskMapper;

    /**
     * 查询任务列
     * 
     * @param id 任务列主键
     * @return 任务列
     */
    @Override
    public TkTasks selectTkTasksById(Long id)
    {
        return tkTasksMapper.selectTkTasksById(id);
    }

    /**
     * 查询任务列列表
     * 
     * @param tkTasks 任务列
     * @return 任务列
     */
    @Override
    public List<TkTasks> selectTkTasksList(TkTasks tkTasks)
    {
        return tkTasksMapper.selectTkTasksList(tkTasks);
    }

    /**
     * 新增任务列
     * 
     * @param tkTasks 任务列
     * @return 结果
     */
    @Override
    public int insertTkTasks(TkTasks tkTasks)
    {
        return tkTasksMapper.insertTkTasks(tkTasks);
    }

    /**
     * 修改任务列
     * 
     * @param tkTasks 任务列
     * @return 结果
     */
    @Override
    public int updateTkTasks(TkTasks tkTasks)
    {
        return tkTasksMapper.updateTkTasks(tkTasks);
    }

    /**
     * 批量删除任务列
     * 
     * @param ids 需要删除的任务列主键
     * @return 结果
     */
    @Override
    public int deleteTkTasksByIds(Long[] ids)
    {
        return tkTasksMapper.deleteTkTasksByIds(ids);
    }

    /**
     * 删除任务列信息
     * 
     * @param id 任务列主键
     * @return 结果
     */
    @Override
    public int deleteTkTasksById(Long id)
    {
        return tkTasksMapper.deleteTkTasksById(id);
    }

    /**
     * 客户端获取任务
     * @param tkTasks
     * @return
     */
    @Override
    public List<TkTasks> getTask(TkTasks tkTasks) {

        //用户领取过的过滤掉
        TkTaskAcceptances tkTaskAcceptances = new TkTaskAcceptances();
        tkTaskAcceptances.setUid(SecurityUtils.getLoginUser().getUser().getUid());
        List<TkTaskAcceptances> tkTaskAcceptances1 = tkTaskAcceptancesMapper.selectTkTaskAcceptancesList(tkTaskAcceptances);//领取任务表
        List<TkTasks> tkTasks1 = tkTasksMapper.selectTkTasksList(tkTasks);//返回的任务

        // 获取 tkTaskAcceptances1 中所有的 taskid
        List<Long> taskIdsToRemove = tkTaskAcceptances1.stream()
                .map(TkTaskAcceptances::getTaskId) // 假设 getTaskId() 是获取 taskid 的方法
                .collect(Collectors.toList());

        // 从 tkTasks1 中移除所有匹配的任务
        List<TkTasks> filteredTasks = tkTasks1.stream()
                .filter(task -> !taskIdsToRemove.contains(task.getId())) // 假设 getId() 是获取任务 id 的方法
                .collect(Collectors.toList());
        return filteredTasks;
    }

    /**
     * 客户端接收任务
     */
    @Override
    @Transactional
    public AjaxResult receiveTask(Long taskId) {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        //只允许接一次
        TkTaskAcceptances tkTaskAcceptances1 = new TkTaskAcceptances();
        tkTaskAcceptances1.setTaskId(taskId);
        tkTaskAcceptances1.setUid(uid);
        List<TkTaskAcceptances> tkTaskAcceptances2 = tkTaskAcceptancesMapper.selectTkTaskAcceptancesList(tkTaskAcceptances1);
        Assert.isTrue(tkTaskAcceptances2.size()==0,"Double collection");

        TkTasks tkTasks = new TkTasks();
        tkTasks.setId(taskId);
        TkTasks TaskList = tkTasksMapper.selectTkTasksList(tkTasks).get(0);

        TkUsers tkUsers = tkUsersMapper.selectTkUsersByUid(uid);
        //等级不符合
        Assert.isTrue(TaskList.getTaskLevel().toString().equals(tkUsers.getSvipLevel().toString()),"Rank error");

        //判断数量
        Assert.isTrue(TaskList.getSurplusquantity()>0,"Quota is full");

        //判断用户是否还有任务额度
        TkTasknum tkTasknum = new TkTasknum();
        tkTasknum.setUserId(uid);
        TkTasknum tkTasknum1 = tkTasknumMapper.selectTkTasknumList(tkTasknum).get(0);
        //判断数量
        if(TaskList.getTaskLevel()==0){
            Assert.isTrue(tkTasknum1.getExperienceTaskCount()>0,"Insufficient quota");
            tkTasknum1.setExperienceTaskCount(tkTasknum1.getExperienceTaskCount()-1);
        }else if(TaskList.getTaskLevel()>0 && TaskList.getTaskLevel()<=5){
            Assert.isTrue(tkTasknum1.getNormalTaskCount()>0,"Insufficient quota");
            tkTasknum1.setNormalTaskCount(tkTasknum1.getNormalTaskCount()-1);
        }else if(TaskList.getTaskLevel()==6){
            Assert.isTrue(tkTasknum1.getHiddenTaskCount()>0,"Insufficient quota");
            tkTasknum1.setHiddenTaskCount(tkTasknum1.getHiddenTaskCount()-1);
        }

        Assert.isTrue( tkTasknumMapper.updateTkTasknum(tkTasknum1)>0,"error");

        //接入任务
        TkTaskAcceptances tkTaskAcceptances = new TkTaskAcceptances();
        tkTaskAcceptances.setTaskId(taskId);
        tkTaskAcceptances.setStatus("0");
        tkTaskAcceptances.setUid(uid);
        tkTaskAcceptances.setCreateTime(new Date());
        //减少一个任务数量
        tkTasks.setSurplusquantity(TaskList.getSurplusquantity()-1);
        int i = tkTasksMapper.updateTkTasks(tkTasks);
        //判断数量
        Assert.isTrue(i!=0,"error");
        //减少一个任务数
        int i1 = tkTaskAcceptancesMapper.insertTkTaskAcceptances(tkTaskAcceptances);
        Assert.isTrue(i1!=0,"error");
        return AjaxResult.success("success");
    }

    @Override
    public AjaxResult getTaskNum() {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        List<Map<String, Object>> taskNum = tkTasksMapper.getTaskNum(uid);
        return AjaxResult.success(taskNum);
    }

    @Override
    public AjaxResult getUserTask(String status) {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        List<UserTaskOV> userTaskList =  tkTasksMapper.getUserTask(status, uid);
        return AjaxResult.success(userTaskList);
    }

    @Override
    @Transactional
    public AjaxResult submitTask(TkTasks tkTasks) {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        Long id = tkTasks.getId();
        String image = tkTasks.getImage();

        Assert.isTrue(id!=null,"error");
        Assert.isTrue(image!=null,"error");



        TkTaskAcceptances tkTaskAcceptances1 = new TkTaskAcceptances();
        tkTaskAcceptances1.setUid(uid);
        tkTaskAcceptances1.setTaskId(id);
        TkTaskAcceptances tkTaskAcceptances = tkTaskAcceptancesMapper.selectTkTaskAcceptancesList(tkTaskAcceptances1).get(0);

        Assert.isTrue(tkTaskAcceptances.getStatus().equals("0"),"error");
        Assert.isTrue(Objects.equals(tkTaskAcceptances.getUid(), uid),"error");

        tkTaskAcceptances.setStatus("1");
        tkTaskAcceptances.setSubmittedImage(image);
        tkTaskAcceptances.setSubmissionTime(new Date());
        int i = tkTaskAcceptancesMapper.updateTkTaskAcceptances(tkTaskAcceptances);
        Assert.isTrue(i!=0,"error");
                //判断是否触发特殊任务
        long specialTaskId = 0;

        TkSpecialTask tkSpecialTask = new TkSpecialTask();
        tkSpecialTask.setUserId(uid);
        List<TkSpecialTask> tkSpecialTasks = tkSpecialTaskMapper.selectTkSpecialTaskList(tkSpecialTask);
        if(tkSpecialTasks.size()!=0){
            for (int i1 = 0; i1 < tkSpecialTasks.size(); i1++) {
                if(tkSpecialTasks.get(i1).getStatus().equals("0") && tkSpecialTasks.get(i1).getTriggerCount()>0){
                    long l = tkSpecialTasks.get(i1).getTriggerCount() - 1;
                    tkSpecialTasks.get(i1).setTriggerCount(l);
                    tkSpecialTaskMapper.updateTkSpecialTask(tkSpecialTasks.get(i1));
                    if(l==0){
                        long taskId = tkSpecialTasks.get(i1).getTaskId();
                        //触发隐藏任务
                        //接入任务
                        TkTaskAcceptances newtkTaskAcceptances = new TkTaskAcceptances();
                        newtkTaskAcceptances.setTaskId(taskId);
                        newtkTaskAcceptances.setStatus("0");
                        newtkTaskAcceptances.setUid(uid);
                        newtkTaskAcceptances.setCreateTime(new Date());
                        tkTaskAcceptancesMapper.insertTkTaskAcceptances(newtkTaskAcceptances);
                        specialTaskId = taskId;
                    }
                }
            }
        }



        AjaxResult ajax = AjaxResult.success();
        if(specialTaskId!=0){
            ajax.put("specialTaskId",specialTaskId);
        }

        return ajax;
    }

    @Override
    public AjaxResult getUserTaskById(Long taskId) {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        return AjaxResult.success(tkTasksMapper.getUserTaskById(taskId,uid));
    }



}

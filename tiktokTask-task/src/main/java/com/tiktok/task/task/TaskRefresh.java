package com.tiktok.task.task;

import com.tiktok.task.domain.TkSvipSetting;
import com.tiktok.task.domain.TkUsers;
import com.tiktok.task.mapper.TkSvipSettingMapper;
import com.tiktok.task.mapper.TkUsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("TaskRefresh")
public class TaskRefresh {
    @Autowired
    private TkUsersMapper tkUsersMapper;
    @Autowired
    private TkSvipSettingMapper tkSvipSettingMapper;
    public void TaskRefresh0()
    {
        //获取出来对应等级的用户
        TkUsers tkUsers = new TkUsers();
        tkUsers.setSvipLevel(0L);
        List<TkUsers> tkUsers1 = tkUsersMapper.selectTkUsersList(tkUsers);

        TkSvipSetting tkSvipSetting = new TkSvipSetting();
        tkSvipSetting.setVipLevel(0L);
        TkSvipSetting tkSvipSetting1 = tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting).get(0);

        Long dailyTaskCount = tkSvipSetting1.getDailyTaskCount();//每日次数
        long[] uids = tkUsers1.stream().mapToLong(TkUsers::getUid).toArray();

        //批量更新
        Map<String, Object> params = new HashMap<>();
        params.put("dailyTaskCount", dailyTaskCount);
        params.put("uids", uids);
        int i = tkSvipSettingMapper.updateDailyTaskCountBatch(params);
    }
    public void TaskRefresh1()
    {
        //获取出来对应等级的用户
        TkUsers tkUsers = new TkUsers();
        tkUsers.setSvipLevel(1L);
        List<TkUsers> tkUsers1 = tkUsersMapper.selectTkUsersList(tkUsers);

        TkSvipSetting tkSvipSetting = new TkSvipSetting();
        tkSvipSetting.setVipLevel(1L);
        TkSvipSetting tkSvipSetting1 = tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting).get(0);

        Long dailyTaskCount = tkSvipSetting1.getDailyTaskCount();//每日次数
        long[] uids = tkUsers1.stream().mapToLong(TkUsers::getUid).toArray();

        //批量更新
        Map<String, Object> params = new HashMap<>();
        params.put("dailyTaskCount", dailyTaskCount);
        params.put("uids", uids);
        int i = tkSvipSettingMapper.updateNormalTaskCountBatch(params);
    }public void TaskRefresh2()
    {
        //获取出来对应等级的用户
        TkUsers tkUsers = new TkUsers();
        tkUsers.setSvipLevel(2L);
        List<TkUsers> tkUsers1 = tkUsersMapper.selectTkUsersList(tkUsers);

        TkSvipSetting tkSvipSetting = new TkSvipSetting();
        tkSvipSetting.setVipLevel(2L);
        TkSvipSetting tkSvipSetting1 = tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting).get(0);

        Long dailyTaskCount = tkSvipSetting1.getDailyTaskCount();//每日次数
        long[] uids = tkUsers1.stream().mapToLong(TkUsers::getUid).toArray();

        //批量更新
        Map<String, Object> params = new HashMap<>();
        params.put("dailyTaskCount", dailyTaskCount);
        params.put("uids", uids);
        int i = tkSvipSettingMapper.updateNormalTaskCountBatch(params);
    }public void TaskRefresh3()
    {
        //获取出来对应等级的用户
        TkUsers tkUsers = new TkUsers();
        tkUsers.setSvipLevel(3L);
        List<TkUsers> tkUsers1 = tkUsersMapper.selectTkUsersList(tkUsers);

        TkSvipSetting tkSvipSetting = new TkSvipSetting();
        tkSvipSetting.setVipLevel(3L);
        TkSvipSetting tkSvipSetting1 = tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting).get(0);

        Long dailyTaskCount = tkSvipSetting1.getDailyTaskCount();//每日次数
        long[] uids = tkUsers1.stream().mapToLong(TkUsers::getUid).toArray();

        //批量更新
        Map<String, Object> params = new HashMap<>();
        params.put("dailyTaskCount", dailyTaskCount);
        params.put("uids", uids);
        int i = tkSvipSettingMapper.updateNormalTaskCountBatch(params);
    }public void TaskRefresh4()
    {
        //获取出来对应等级的用户
        TkUsers tkUsers = new TkUsers();
        tkUsers.setSvipLevel(4L);
        List<TkUsers> tkUsers1 = tkUsersMapper.selectTkUsersList(tkUsers);

        TkSvipSetting tkSvipSetting = new TkSvipSetting();
        tkSvipSetting.setVipLevel(4L);
        TkSvipSetting tkSvipSetting1 = tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting).get(0);

        Long dailyTaskCount = tkSvipSetting1.getDailyTaskCount();//每日次数
        long[] uids = tkUsers1.stream().mapToLong(TkUsers::getUid).toArray();

        //批量更新
        Map<String, Object> params = new HashMap<>();
        params.put("dailyTaskCount", dailyTaskCount);
        params.put("uids", uids);
        int i = tkSvipSettingMapper.updateNormalTaskCountBatch(params);
    }public void TaskRefresh5()
    {
        //获取出来对应等级的用户
        TkUsers tkUsers = new TkUsers();
        tkUsers.setSvipLevel(5L);
        List<TkUsers> tkUsers1 = tkUsersMapper.selectTkUsersList(tkUsers);

        TkSvipSetting tkSvipSetting = new TkSvipSetting();
        tkSvipSetting.setVipLevel(5L);
        TkSvipSetting tkSvipSetting1 = tkSvipSettingMapper.selectTkSvipSettingList(tkSvipSetting).get(0);

        Long dailyTaskCount = tkSvipSetting1.getDailyTaskCount();//每日次数
        long[] uids = tkUsers1.stream().mapToLong(TkUsers::getUid).toArray();

        //批量更新
        Map<String, Object> params = new HashMap<>();
        params.put("dailyTaskCount", dailyTaskCount);
        params.put("uids", uids);
        int i = tkSvipSettingMapper.updateNormalTaskCountBatch(params);
    }



}

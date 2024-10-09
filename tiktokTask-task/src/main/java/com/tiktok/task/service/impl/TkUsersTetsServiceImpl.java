package com.tiktok.task.service.impl;

import java.util.List;
import com.tiktok.common.utils.DateUtils;
import com.tiktok.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkUsersTetsMapper;
import com.tiktok.task.domain.TkUsersTets;
import com.tiktok.task.service.ITkUsersTetsService;

/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-02
 */
@Service
public class TkUsersTetsServiceImpl implements ITkUsersTetsService 
{
    @Autowired
    private TkUsersTetsMapper tkUsersTetsMapper;

    /**
     * 查询用户信息
     * 
     * @param uid 用户信息主键
     * @return 用户信息
     */
    @Override
    public TkUsersTets selectTkUsersTetsByUid(Long uid)
    {
        return tkUsersTetsMapper.selectTkUsersTetsByUid(uid);
    }

    /**
     * 查询用户信息列表
     * 
     * @param tkUsersTets 用户信息
     * @return 用户信息
     */
    @Override
    public List<TkUsersTets> selectTkUsersTetsList(TkUsersTets tkUsersTets)
    {
        tkUsersTets.setCreateBy(SecurityUtils.getUserId().toString());
        return tkUsersTetsMapper.selectTkUsersTetsList(tkUsersTets);
    }

    /**
     * 新增用户信息
     * 
     * @param tkUsersTets 用户信息
     * @return 结果
     */
    @Override
    public int insertTkUsersTets(TkUsersTets tkUsersTets)
    {
        tkUsersTets.setCreateTime(DateUtils.getNowDate());
        return tkUsersTetsMapper.insertTkUsersTets(tkUsersTets);
    }

    /**
     * 修改用户信息
     * 
     * @param tkUsersTets 用户信息
     * @return 结果
     */
    @Override
    public int updateTkUsersTets(TkUsersTets tkUsersTets)
    {
        tkUsersTets.setUpdateTime(DateUtils.getNowDate());
        return tkUsersTetsMapper.updateTkUsersTets(tkUsersTets);
    }

    /**
     * 批量删除用户信息
     * 
     * @param uids 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTkUsersTetsByUids(Long[] uids)
    {
        return tkUsersTetsMapper.deleteTkUsersTetsByUids(uids);
    }

    /**
     * 删除用户信息信息
     * 
     * @param uid 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteTkUsersTetsByUid(Long uid)
    {
        return tkUsersTetsMapper.deleteTkUsersTetsByUid(uid);
    }
}

package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkUserDefaultMapper;
import com.tiktok.task.domain.TkUserDefault;
import com.tiktok.task.service.ITkUserDefaultService;

/**
 * 系统默认配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-27
 */
@Service
public class TkUserDefaultServiceImpl implements ITkUserDefaultService 
{
    @Autowired
    private TkUserDefaultMapper tkUserDefaultMapper;

    /**
     * 查询系统默认配置
     * 
     * @param id 系统默认配置主键
     * @return 系统默认配置
     */
    @Override
    public TkUserDefault selectTkUserDefaultById(Long id)
    {
        return tkUserDefaultMapper.selectTkUserDefaultById(id);
    }

    /**
     * 查询系统默认配置列表
     * 
     * @param tkUserDefault 系统默认配置
     * @return 系统默认配置
     */
    @Override
    public List<TkUserDefault> selectTkUserDefaultList(TkUserDefault tkUserDefault)
    {
        return tkUserDefaultMapper.selectTkUserDefaultList(tkUserDefault);
    }

    /**
     * 新增系统默认配置
     * 
     * @param tkUserDefault 系统默认配置
     * @return 结果
     */
    @Override
    public int insertTkUserDefault(TkUserDefault tkUserDefault)
    {
        return tkUserDefaultMapper.insertTkUserDefault(tkUserDefault);
    }

    /**
     * 修改系统默认配置
     * 
     * @param tkUserDefault 系统默认配置
     * @return 结果
     */
    @Override
    public int updateTkUserDefault(TkUserDefault tkUserDefault)
    {
        return tkUserDefaultMapper.updateTkUserDefault(tkUserDefault);
    }

    /**
     * 批量删除系统默认配置
     * 
     * @param ids 需要删除的系统默认配置主键
     * @return 结果
     */
    @Override
    public int deleteTkUserDefaultByIds(Long[] ids)
    {
        return tkUserDefaultMapper.deleteTkUserDefaultByIds(ids);
    }

    /**
     * 删除系统默认配置信息
     * 
     * @param id 系统默认配置主键
     * @return 结果
     */
    @Override
    public int deleteTkUserDefaultById(Long id)
    {
        return tkUserDefaultMapper.deleteTkUserDefaultById(id);
    }
}

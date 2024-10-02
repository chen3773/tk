package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkRoyaltySettingMapper;
import com.tiktok.task.domain.TkRoyaltySetting;
import com.tiktok.task.service.ITkRoyaltySettingService;

/**
 * 三级分佣百分比Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-27
 */
@Service
public class TkRoyaltySettingServiceImpl implements ITkRoyaltySettingService 
{
    @Autowired
    private TkRoyaltySettingMapper tkRoyaltySettingMapper;

    /**
     * 查询三级分佣百分比
     * 
     * @param id 三级分佣百分比主键
     * @return 三级分佣百分比
     */
    @Override
    public TkRoyaltySetting selectTkRoyaltySettingById(Long id)
    {
        return tkRoyaltySettingMapper.selectTkRoyaltySettingById(id);
    }

    /**
     * 查询三级分佣百分比列表
     * 
     * @param tkRoyaltySetting 三级分佣百分比
     * @return 三级分佣百分比
     */
    @Override
    public List<TkRoyaltySetting> selectTkRoyaltySettingList(TkRoyaltySetting tkRoyaltySetting)
    {
        return tkRoyaltySettingMapper.selectTkRoyaltySettingList(tkRoyaltySetting);
    }

    /**
     * 新增三级分佣百分比
     * 
     * @param tkRoyaltySetting 三级分佣百分比
     * @return 结果
     */
    @Override
    public int insertTkRoyaltySetting(TkRoyaltySetting tkRoyaltySetting)
    {
        return tkRoyaltySettingMapper.insertTkRoyaltySetting(tkRoyaltySetting);
    }

    /**
     * 修改三级分佣百分比
     * 
     * @param tkRoyaltySetting 三级分佣百分比
     * @return 结果
     */
    @Override
    public int updateTkRoyaltySetting(TkRoyaltySetting tkRoyaltySetting)
    {
        return tkRoyaltySettingMapper.updateTkRoyaltySetting(tkRoyaltySetting);
    }

    /**
     * 批量删除三级分佣百分比
     * 
     * @param ids 需要删除的三级分佣百分比主键
     * @return 结果
     */
    @Override
    public int deleteTkRoyaltySettingByIds(Long[] ids)
    {
        return tkRoyaltySettingMapper.deleteTkRoyaltySettingByIds(ids);
    }

    /**
     * 删除三级分佣百分比信息
     * 
     * @param id 三级分佣百分比主键
     * @return 结果
     */
    @Override
    public int deleteTkRoyaltySettingById(Long id)
    {
        return tkRoyaltySettingMapper.deleteTkRoyaltySettingById(id);
    }
}

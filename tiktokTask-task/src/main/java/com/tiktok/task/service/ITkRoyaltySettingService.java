package com.tiktok.task.service;

import java.util.List;
import com.tiktok.task.domain.TkRoyaltySetting;

/**
 * 三级分佣百分比Service接口
 * 
 * @author ruoyi
 * @date 2024-09-27
 */
public interface ITkRoyaltySettingService 
{
    /**
     * 查询三级分佣百分比
     * 
     * @param id 三级分佣百分比主键
     * @return 三级分佣百分比
     */
    public TkRoyaltySetting selectTkRoyaltySettingById(Long id);

    /**
     * 查询三级分佣百分比列表
     * 
     * @param tkRoyaltySetting 三级分佣百分比
     * @return 三级分佣百分比集合
     */
    public List<TkRoyaltySetting> selectTkRoyaltySettingList(TkRoyaltySetting tkRoyaltySetting);

    /**
     * 新增三级分佣百分比
     * 
     * @param tkRoyaltySetting 三级分佣百分比
     * @return 结果
     */
    public int insertTkRoyaltySetting(TkRoyaltySetting tkRoyaltySetting);

    /**
     * 修改三级分佣百分比
     * 
     * @param tkRoyaltySetting 三级分佣百分比
     * @return 结果
     */
    public int updateTkRoyaltySetting(TkRoyaltySetting tkRoyaltySetting);

    /**
     * 批量删除三级分佣百分比
     * 
     * @param ids 需要删除的三级分佣百分比主键集合
     * @return 结果
     */
    public int deleteTkRoyaltySettingByIds(Long[] ids);

    /**
     * 删除三级分佣百分比信息
     * 
     * @param id 三级分佣百分比主键
     * @return 结果
     */
    public int deleteTkRoyaltySettingById(Long id);
}

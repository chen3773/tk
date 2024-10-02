package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkRoyaltySetting;

/**
 * 三级分佣百分比Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-27
 */
public interface TkRoyaltySettingMapper 
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
     * 删除三级分佣百分比
     * 
     * @param id 三级分佣百分比主键
     * @return 结果
     */
    public int deleteTkRoyaltySettingById(Long id);

    /**
     * 批量删除三级分佣百分比
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkRoyaltySettingByIds(Long[] ids);
}

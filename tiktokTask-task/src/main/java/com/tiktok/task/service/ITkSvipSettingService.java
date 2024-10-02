package com.tiktok.task.service;

import java.util.List;
import com.tiktok.task.domain.TkSvipSetting;

/**
 * svip默认配置Service接口
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
public interface ITkSvipSettingService 
{
    /**
     * 查询svip默认配置
     * 
     * @param id svip默认配置主键
     * @return svip默认配置
     */
    public TkSvipSetting selectTkSvipSettingById(Long id);

    /**
     * 查询svip默认配置列表
     * 
     * @param tkSvipSetting svip默认配置
     * @return svip默认配置集合
     */
    public List<TkSvipSetting> selectTkSvipSettingList(TkSvipSetting tkSvipSetting);

    /**
     * 新增svip默认配置
     * 
     * @param tkSvipSetting svip默认配置
     * @return 结果
     */
    public int insertTkSvipSetting(TkSvipSetting tkSvipSetting);

    /**
     * 修改svip默认配置
     * 
     * @param tkSvipSetting svip默认配置
     * @return 结果
     */
    public int updateTkSvipSetting(TkSvipSetting tkSvipSetting);

    /**
     * 批量删除svip默认配置
     * 
     * @param ids 需要删除的svip默认配置主键集合
     * @return 结果
     */
    public int deleteTkSvipSettingByIds(Long[] ids);

    /**
     * 删除svip默认配置信息
     * 
     * @param id svip默认配置主键
     * @return 结果
     */
    public int deleteTkSvipSettingById(Long id);

    int UpgradeSvip(Long uid, Long lv);
}

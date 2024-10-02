package com.tiktok.task.service;

import java.util.List;
import com.tiktok.task.domain.TkUserDefault;

/**
 * 系统默认配置Service接口
 * 
 * @author ruoyi
 * @date 2024-09-27
 */
public interface ITkUserDefaultService 
{
    /**
     * 查询系统默认配置
     * 
     * @param id 系统默认配置主键
     * @return 系统默认配置
     */
    public TkUserDefault selectTkUserDefaultById(Long id);

    /**
     * 查询系统默认配置列表
     * 
     * @param tkUserDefault 系统默认配置
     * @return 系统默认配置集合
     */
    public List<TkUserDefault> selectTkUserDefaultList(TkUserDefault tkUserDefault);

    /**
     * 新增系统默认配置
     * 
     * @param tkUserDefault 系统默认配置
     * @return 结果
     */
    public int insertTkUserDefault(TkUserDefault tkUserDefault);

    /**
     * 修改系统默认配置
     * 
     * @param tkUserDefault 系统默认配置
     * @return 结果
     */
    public int updateTkUserDefault(TkUserDefault tkUserDefault);

    /**
     * 批量删除系统默认配置
     * 
     * @param ids 需要删除的系统默认配置主键集合
     * @return 结果
     */
    public int deleteTkUserDefaultByIds(Long[] ids);

    /**
     * 删除系统默认配置信息
     * 
     * @param id 系统默认配置主键
     * @return 结果
     */
    public int deleteTkUserDefaultById(Long id);
}

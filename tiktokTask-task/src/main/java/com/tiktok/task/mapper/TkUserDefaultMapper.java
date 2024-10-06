package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkUserDefault;

/**
 * 系统默认配置Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-06
 */
public interface TkUserDefaultMapper 
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
     * 删除系统默认配置
     * 
     * @param id 系统默认配置主键
     * @return 结果
     */
    public int deleteTkUserDefaultById(Long id);

    /**
     * 批量删除系统默认配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkUserDefaultByIds(Long[] ids);
}

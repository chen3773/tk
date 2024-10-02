package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkTaskAcceptances;

/**
 * 用户接取任务Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
public interface TkTaskAcceptancesMapper 
{
    /**
     * 查询用户接取任务
     * 
     * @param id 用户接取任务主键
     * @return 用户接取任务
     */
    public TkTaskAcceptances selectTkTaskAcceptancesById(Long id);

    /**
     * 查询用户接取任务列表
     * 
     * @param tkTaskAcceptances 用户接取任务
     * @return 用户接取任务集合
     */
    public List<TkTaskAcceptances> selectTkTaskAcceptancesList(TkTaskAcceptances tkTaskAcceptances);

    /**
     * 新增用户接取任务
     * 
     * @param tkTaskAcceptances 用户接取任务
     * @return 结果
     */
    public int insertTkTaskAcceptances(TkTaskAcceptances tkTaskAcceptances);

    /**
     * 修改用户接取任务
     * 
     * @param tkTaskAcceptances 用户接取任务
     * @return 结果
     */
    public int updateTkTaskAcceptances(TkTaskAcceptances tkTaskAcceptances);

    /**
     * 删除用户接取任务
     * 
     * @param id 用户接取任务主键
     * @return 结果
     */
    public int deleteTkTaskAcceptancesById(Long id);

    /**
     * 批量删除用户接取任务
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkTaskAcceptancesByIds(Long[] ids);


    public List<TkTaskAcceptances> selectUserSubmitToday(Long uid);
}

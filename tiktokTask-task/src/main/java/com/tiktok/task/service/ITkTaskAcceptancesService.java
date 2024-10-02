package com.tiktok.task.service;

import java.util.List;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.task.domain.TkTaskAcceptances;

/**
 * 用户接取任务Service接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
public interface ITkTaskAcceptancesService 
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
     * 批量删除用户接取任务
     * 
     * @param ids 需要删除的用户接取任务主键集合
     * @return 结果
     */
    public int deleteTkTaskAcceptancesByIds(Long[] ids);

    /**
     * 删除用户接取任务信息
     * 
     * @param id 用户接取任务主键
     * @return 结果
     */
    public int deleteTkTaskAcceptancesById(Long id);

    public AjaxResult TaskAudit(Long id, boolean pass);
}

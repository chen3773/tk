package com.tiktok.task.service;

import java.util.List;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.framework.web.exception.CustomException;
import com.tiktok.task.domain.TkTasks;

/**
 * 任务列Service接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
public interface ITkTasksService 
{
    /**
     * 查询任务列
     * 
     * @param id 任务列主键
     * @return 任务列
     */
    public TkTasks selectTkTasksById(Long id);

    /**
     * 查询任务列列表
     * 
     * @param tkTasks 任务列
     * @return 任务列集合
     */
    public List<TkTasks> selectTkTasksList(TkTasks tkTasks);

    /**
     * 新增任务列
     * 
     * @param tkTasks 任务列
     * @return 结果
     */
    public int insertTkTasks(TkTasks tkTasks);

    /**
     * 修改任务列
     * 
     * @param tkTasks 任务列
     * @return 结果
     */
    public int updateTkTasks(TkTasks tkTasks);

    /**
     * 批量删除任务列
     * 
     * @param ids 需要删除的任务列主键集合
     * @return 结果
     */
    public int deleteTkTasksByIds(Long[] ids);

    /**
     * 删除任务列信息
     * 
     * @param id 任务列主键
     * @return 结果
     */
    public int deleteTkTasksById(Long id);

    List<TkTasks> getTask(TkTasks tkTasks);

    AjaxResult receiveTask(Long taskId) throws CustomException;

    AjaxResult getTaskNum();

    AjaxResult getUserTask(String status);

    AjaxResult submitTask(TkTasks tkTasks);

    AjaxResult getUserTaskById(Long id);

}

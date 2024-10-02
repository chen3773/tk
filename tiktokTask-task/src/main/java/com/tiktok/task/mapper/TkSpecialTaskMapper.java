package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkSpecialTask;

/**
 * 特殊任务触发Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
public interface TkSpecialTaskMapper 
{
    /**
     * 查询特殊任务触发
     * 
     * @param id 特殊任务触发主键
     * @return 特殊任务触发
     */
    public TkSpecialTask selectTkSpecialTaskById(Long id);

    /**
     * 查询特殊任务触发列表
     * 
     * @param tkSpecialTask 特殊任务触发
     * @return 特殊任务触发集合
     */
    public List<TkSpecialTask> selectTkSpecialTaskList(TkSpecialTask tkSpecialTask);

    /**
     * 新增特殊任务触发
     * 
     * @param tkSpecialTask 特殊任务触发
     * @return 结果
     */
    public int insertTkSpecialTask(TkSpecialTask tkSpecialTask);

    /**
     * 修改特殊任务触发
     * 
     * @param tkSpecialTask 特殊任务触发
     * @return 结果
     */
    public int updateTkSpecialTask(TkSpecialTask tkSpecialTask);

    /**
     * 删除特殊任务触发
     * 
     * @param id 特殊任务触发主键
     * @return 结果
     */
    public int deleteTkSpecialTaskById(Long id);

    /**
     * 批量删除特殊任务触发
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkSpecialTaskByIds(Long[] ids);
}

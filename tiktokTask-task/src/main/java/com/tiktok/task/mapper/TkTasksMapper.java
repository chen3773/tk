package com.tiktok.task.mapper;

import java.util.List;
import java.util.Map;

import com.tiktok.task.domain.TkTasks;
import com.tiktok.task.domain.ov.UserTaskOV;
import org.apache.ibatis.annotations.Param;

/**
 * 任务列Mapper接口
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
public interface TkTasksMapper 
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
    public List<TkTasks> selectRandomTkTasks(TkTasks tkTasks);

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
     * 删除任务列
     * 
     * @param id 任务列主键
     * @return 结果
     */
    public int deleteTkTasksById(Long id);

    /**
     * 批量删除任务列
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkTasksByIds(Long[] ids);

    public List<Map<String, Object>> getTaskNum(@Param("uid") Long uid);

    List<UserTaskOV> getUserTask(@Param("status") String status, @Param("uid") Long uid);

    List<UserTaskOV> getUserTaskById(@Param("taskId") Long taskId,@Param("uid") Long uid);
    int batchUpdateTasks(@Param("idList") List<Long> idList,
                             @Param("rewardAmount") String rewardAmount,
                             @Param("title") String title);


}

package com.tiktok.task.service.impl;

import java.util.List;
import com.tiktok.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkSpecialTaskMapper;
import com.tiktok.task.domain.TkSpecialTask;
import com.tiktok.task.service.ITkSpecialTaskService;

/**
 * 特殊任务触发Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@Service
public class TkSpecialTaskServiceImpl implements ITkSpecialTaskService 
{
    @Autowired
    private TkSpecialTaskMapper tkSpecialTaskMapper;

    /**
     * 查询特殊任务触发
     * 
     * @param id 特殊任务触发主键
     * @return 特殊任务触发
     */
    @Override
    public TkSpecialTask selectTkSpecialTaskById(Long id)
    {
        return tkSpecialTaskMapper.selectTkSpecialTaskById(id);
    }

    /**
     * 查询特殊任务触发列表
     * 
     * @param tkSpecialTask 特殊任务触发
     * @return 特殊任务触发
     */
    @Override
    public List<TkSpecialTask> selectTkSpecialTaskList(TkSpecialTask tkSpecialTask)
    {
        return tkSpecialTaskMapper.selectTkSpecialTaskList(tkSpecialTask);
    }

    /**
     * 新增特殊任务触发
     * 
     * @param tkSpecialTask 特殊任务触发
     * @return 结果
     */
    @Override
    public int insertTkSpecialTask(TkSpecialTask tkSpecialTask)
    {
        tkSpecialTask.setCreateTime(DateUtils.getNowDate());
        return tkSpecialTaskMapper.insertTkSpecialTask(tkSpecialTask);
    }

    /**
     * 修改特殊任务触发
     * 
     * @param tkSpecialTask 特殊任务触发
     * @return 结果
     */
    @Override
    public int updateTkSpecialTask(TkSpecialTask tkSpecialTask)
    {
        tkSpecialTask.setUpdateTime(DateUtils.getNowDate());
        return tkSpecialTaskMapper.updateTkSpecialTask(tkSpecialTask);
    }

    /**
     * 批量删除特殊任务触发
     * 
     * @param ids 需要删除的特殊任务触发主键
     * @return 结果
     */
    @Override
    public int deleteTkSpecialTaskByIds(Long[] ids)
    {
        return tkSpecialTaskMapper.deleteTkSpecialTaskByIds(ids);
    }

    /**
     * 删除特殊任务触发信息
     * 
     * @param id 特殊任务触发主键
     * @return 结果
     */
    @Override
    public int deleteTkSpecialTaskById(Long id)
    {
        return tkSpecialTaskMapper.deleteTkSpecialTaskById(id);
    }
}

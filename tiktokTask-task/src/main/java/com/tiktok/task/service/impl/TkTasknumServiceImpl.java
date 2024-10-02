package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkTasknumMapper;
import com.tiktok.task.domain.TkTasknum;
import com.tiktok.task.service.ITkTasknumService;

/**
 * 任务数量Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@Service
public class TkTasknumServiceImpl implements ITkTasknumService 
{
    @Autowired
    private TkTasknumMapper tkTasknumMapper;

    /**
     * 查询任务数量
     * 
     * @param id 任务数量主键
     * @return 任务数量
     */
    @Override
    public TkTasknum selectTkTasknumById(Long id)
    {
        return tkTasknumMapper.selectTkTasknumById(id);
    }

    /**
     * 查询任务数量列表
     * 
     * @param tkTasknum 任务数量
     * @return 任务数量
     */
    @Override
    public List<TkTasknum> selectTkTasknumList(TkTasknum tkTasknum)
    {
        return tkTasknumMapper.selectTkTasknumList(tkTasknum);
    }

    /**
     * 新增任务数量
     * 
     * @param tkTasknum 任务数量
     * @return 结果
     */
    @Override
    public int insertTkTasknum(TkTasknum tkTasknum)
    {
        return tkTasknumMapper.insertTkTasknum(tkTasknum);
    }

    /**
     * 修改任务数量
     * 
     * @param tkTasknum 任务数量
     * @return 结果
     */
    @Override
    public int updateTkTasknum(TkTasknum tkTasknum)
    {
        return tkTasknumMapper.updateTkTasknum(tkTasknum);
    }

    /**
     * 批量删除任务数量
     * 
     * @param ids 需要删除的任务数量主键
     * @return 结果
     */
    @Override
    public int deleteTkTasknumByIds(Long[] ids)
    {
        return tkTasknumMapper.deleteTkTasknumByIds(ids);
    }

    /**
     * 删除任务数量信息
     * 
     * @param id 任务数量主键
     * @return 结果
     */
    @Override
    public int deleteTkTasknumById(Long id)
    {
        return tkTasknumMapper.deleteTkTasknumById(id);
    }
}

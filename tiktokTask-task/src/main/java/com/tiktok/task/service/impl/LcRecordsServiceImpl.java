package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.LcRecordsMapper;
import com.tiktok.task.domain.LcRecords;
import com.tiktok.task.service.ILcRecordsService;

/**
 * 理财分红记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-24
 */
@Service
public class LcRecordsServiceImpl implements ILcRecordsService 
{
    @Autowired
    private LcRecordsMapper lcRecordsMapper;

    /**
     * 查询理财分红记录
     * 
     * @param id 理财分红记录主键
     * @return 理财分红记录
     */
    @Override
    public LcRecords selectLcRecordsById(Long id)
    {
        return lcRecordsMapper.selectLcRecordsById(id);
    }

    /**
     * 查询理财分红记录列表
     * 
     * @param lcRecords 理财分红记录
     * @return 理财分红记录
     */
    @Override
    public List<LcRecords> selectLcRecordsList(LcRecords lcRecords)
    {
        return lcRecordsMapper.selectLcRecordsList(lcRecords);
    }

    /**
     * 新增理财分红记录
     * 
     * @param lcRecords 理财分红记录
     * @return 结果
     */
    @Override
    public int insertLcRecords(LcRecords lcRecords)
    {
        return lcRecordsMapper.insertLcRecords(lcRecords);
    }

    /**
     * 修改理财分红记录
     * 
     * @param lcRecords 理财分红记录
     * @return 结果
     */
    @Override
    public int updateLcRecords(LcRecords lcRecords)
    {
        return lcRecordsMapper.updateLcRecords(lcRecords);
    }

    /**
     * 批量删除理财分红记录
     * 
     * @param ids 需要删除的理财分红记录主键
     * @return 结果
     */
    @Override
    public int deleteLcRecordsByIds(Long[] ids)
    {
        return lcRecordsMapper.deleteLcRecordsByIds(ids);
    }

    /**
     * 删除理财分红记录信息
     * 
     * @param id 理财分红记录主键
     * @return 结果
     */
    @Override
    public int deleteLcRecordsById(Long id)
    {
        return lcRecordsMapper.deleteLcRecordsById(id);
    }
}

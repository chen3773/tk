package com.tiktok.task.service;

import java.util.List;
import com.tiktok.task.domain.LcRecords;

/**
 * 理财分红记录Service接口
 * 
 * @author ruoyi
 * @date 2024-10-24
 */
public interface ILcRecordsService 
{
    /**
     * 查询理财分红记录
     * 
     * @param id 理财分红记录主键
     * @return 理财分红记录
     */
    public LcRecords selectLcRecordsById(Long id);

    /**
     * 查询理财分红记录列表
     * 
     * @param lcRecords 理财分红记录
     * @return 理财分红记录集合
     */
    public List<LcRecords> selectLcRecordsList(LcRecords lcRecords);

    /**
     * 新增理财分红记录
     * 
     * @param lcRecords 理财分红记录
     * @return 结果
     */
    public int insertLcRecords(LcRecords lcRecords);

    /**
     * 修改理财分红记录
     * 
     * @param lcRecords 理财分红记录
     * @return 结果
     */
    public int updateLcRecords(LcRecords lcRecords);

    /**
     * 批量删除理财分红记录
     * 
     * @param ids 需要删除的理财分红记录主键集合
     * @return 结果
     */
    public int deleteLcRecordsByIds(Long[] ids);

    /**
     * 删除理财分红记录信息
     * 
     * @param id 理财分红记录主键
     * @return 结果
     */
    public int deleteLcRecordsById(Long id);
}

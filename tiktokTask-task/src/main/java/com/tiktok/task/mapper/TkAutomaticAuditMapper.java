package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkAutomaticAudit;

/**
 * 自动审核Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
public interface TkAutomaticAuditMapper 
{
    /**
     * 查询自动审核
     * 
     * @param id 自动审核主键
     * @return 自动审核
     */
    public TkAutomaticAudit selectTkAutomaticAuditById(Long id);

    /**
     * 查询自动审核列表
     * 
     * @param tkAutomaticAudit 自动审核
     * @return 自动审核集合
     */
    public List<TkAutomaticAudit> selectTkAutomaticAuditList(TkAutomaticAudit tkAutomaticAudit);

    /**
     * 新增自动审核
     * 
     * @param tkAutomaticAudit 自动审核
     * @return 结果
     */
    public int insertTkAutomaticAudit(TkAutomaticAudit tkAutomaticAudit);

    /**
     * 修改自动审核
     * 
     * @param tkAutomaticAudit 自动审核
     * @return 结果
     */
    public int updateTkAutomaticAudit(TkAutomaticAudit tkAutomaticAudit);

    /**
     * 删除自动审核
     * 
     * @param id 自动审核主键
     * @return 结果
     */
    public int deleteTkAutomaticAuditById(Long id);

    /**
     * 批量删除自动审核
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkAutomaticAuditByIds(Long[] ids);
}

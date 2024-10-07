package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkAutomaticAuditMapper;
import com.tiktok.task.domain.TkAutomaticAudit;
import com.tiktok.task.service.ITkAutomaticAuditService;

/**
 * 自动审核Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
@Service
public class TkAutomaticAuditServiceImpl implements ITkAutomaticAuditService 
{
    @Autowired
    private TkAutomaticAuditMapper tkAutomaticAuditMapper;

    /**
     * 查询自动审核
     * 
     * @param id 自动审核主键
     * @return 自动审核
     */
    @Override
    public TkAutomaticAudit selectTkAutomaticAuditById(Long id)
    {
        return tkAutomaticAuditMapper.selectTkAutomaticAuditById(id);
    }

    /**
     * 查询自动审核列表
     * 
     * @param tkAutomaticAudit 自动审核
     * @return 自动审核
     */
    @Override
    public List<TkAutomaticAudit> selectTkAutomaticAuditList(TkAutomaticAudit tkAutomaticAudit)
    {
        return tkAutomaticAuditMapper.selectTkAutomaticAuditList(tkAutomaticAudit);
    }

    /**
     * 新增自动审核
     * 
     * @param tkAutomaticAudit 自动审核
     * @return 结果
     */
    @Override
    public int insertTkAutomaticAudit(TkAutomaticAudit tkAutomaticAudit)
    {
        return tkAutomaticAuditMapper.insertTkAutomaticAudit(tkAutomaticAudit);
    }

    /**
     * 修改自动审核
     * 
     * @param tkAutomaticAudit 自动审核
     * @return 结果
     */
    @Override
    public int updateTkAutomaticAudit(TkAutomaticAudit tkAutomaticAudit)
    {
        return tkAutomaticAuditMapper.updateTkAutomaticAudit(tkAutomaticAudit);
    }

    /**
     * 批量删除自动审核
     * 
     * @param ids 需要删除的自动审核主键
     * @return 结果
     */
    @Override
    public int deleteTkAutomaticAuditByIds(Long[] ids)
    {
        return tkAutomaticAuditMapper.deleteTkAutomaticAuditByIds(ids);
    }

    /**
     * 删除自动审核信息
     * 
     * @param id 自动审核主键
     * @return 结果
     */
    @Override
    public int deleteTkAutomaticAuditById(Long id)
    {
        return tkAutomaticAuditMapper.deleteTkAutomaticAuditById(id);
    }
}

package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkAgreement;

/**
 * 文本Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
public interface TkAgreementMapper 
{
    /**
     * 查询文本
     * 
     * @param id 文本主键
     * @return 文本
     */
    public TkAgreement selectTkAgreementById(Long id);

    /**
     * 查询文本列表
     * 
     * @param tkAgreement 文本
     * @return 文本集合
     */
    public List<TkAgreement> selectTkAgreementList(TkAgreement tkAgreement);

    /**
     * 新增文本
     * 
     * @param tkAgreement 文本
     * @return 结果
     */
    public int insertTkAgreement(TkAgreement tkAgreement);

    /**
     * 修改文本
     * 
     * @param tkAgreement 文本
     * @return 结果
     */
    public int updateTkAgreement(TkAgreement tkAgreement);

    /**
     * 删除文本
     * 
     * @param id 文本主键
     * @return 结果
     */
    public int deleteTkAgreementById(Long id);

    /**
     * 批量删除文本
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkAgreementByIds(Long[] ids);
}

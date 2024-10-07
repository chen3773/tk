package com.tiktok.task.service;

import java.util.List;
import com.tiktok.task.domain.TkBlockchain;

/**
 * 区块链名称Service接口
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
public interface ITkBlockchainService 
{
    /**
     * 查询区块链名称
     * 
     * @param id 区块链名称主键
     * @return 区块链名称
     */
    public TkBlockchain selectTkBlockchainById(Long id);

    /**
     * 查询区块链名称列表
     * 
     * @param tkBlockchain 区块链名称
     * @return 区块链名称集合
     */
    public List<TkBlockchain> selectTkBlockchainList(TkBlockchain tkBlockchain);

    /**
     * 新增区块链名称
     * 
     * @param tkBlockchain 区块链名称
     * @return 结果
     */
    public int insertTkBlockchain(TkBlockchain tkBlockchain);

    /**
     * 修改区块链名称
     * 
     * @param tkBlockchain 区块链名称
     * @return 结果
     */
    public int updateTkBlockchain(TkBlockchain tkBlockchain);

    /**
     * 批量删除区块链名称
     * 
     * @param ids 需要删除的区块链名称主键集合
     * @return 结果
     */
    public int deleteTkBlockchainByIds(Long[] ids);

    /**
     * 删除区块链名称信息
     * 
     * @param id 区块链名称主键
     * @return 结果
     */
    public int deleteTkBlockchainById(Long id);
}

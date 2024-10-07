package com.tiktok.task.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.TkBlockchainMapper;
import com.tiktok.task.domain.TkBlockchain;
import com.tiktok.task.service.ITkBlockchainService;

/**
 * 区块链名称Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
@Service
public class TkBlockchainServiceImpl implements ITkBlockchainService 
{
    @Autowired
    private TkBlockchainMapper tkBlockchainMapper;

    /**
     * 查询区块链名称
     * 
     * @param id 区块链名称主键
     * @return 区块链名称
     */
    @Override
    public TkBlockchain selectTkBlockchainById(Long id)
    {
        return tkBlockchainMapper.selectTkBlockchainById(id);
    }

    /**
     * 查询区块链名称列表
     * 
     * @param tkBlockchain 区块链名称
     * @return 区块链名称
     */
    @Override
    public List<TkBlockchain> selectTkBlockchainList(TkBlockchain tkBlockchain)
    {
        return tkBlockchainMapper.selectTkBlockchainList(tkBlockchain);
    }

    /**
     * 新增区块链名称
     * 
     * @param tkBlockchain 区块链名称
     * @return 结果
     */
    @Override
    public int insertTkBlockchain(TkBlockchain tkBlockchain)
    {
        return tkBlockchainMapper.insertTkBlockchain(tkBlockchain);
    }

    /**
     * 修改区块链名称
     * 
     * @param tkBlockchain 区块链名称
     * @return 结果
     */
    @Override
    public int updateTkBlockchain(TkBlockchain tkBlockchain)
    {
        return tkBlockchainMapper.updateTkBlockchain(tkBlockchain);
    }

    /**
     * 批量删除区块链名称
     * 
     * @param ids 需要删除的区块链名称主键
     * @return 结果
     */
    @Override
    public int deleteTkBlockchainByIds(Long[] ids)
    {
        return tkBlockchainMapper.deleteTkBlockchainByIds(ids);
    }

    /**
     * 删除区块链名称信息
     * 
     * @param id 区块链名称主键
     * @return 结果
     */
    @Override
    public int deleteTkBlockchainById(Long id)
    {
        return tkBlockchainMapper.deleteTkBlockchainById(id);
    }
}

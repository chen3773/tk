package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.TkUsersTets;

/**
 * 用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-02
 */
public interface TkUsersTetsMapper 
{
    /**
     * 查询用户信息
     * 
     * @param uid 用户信息主键
     * @return 用户信息
     */
    public TkUsersTets selectTkUsersTetsByUid(Long uid);

    /**
     * 查询用户信息列表
     * 
     * @param tkUsersTets 用户信息
     * @return 用户信息集合
     */
    public List<TkUsersTets> selectTkUsersTetsList(TkUsersTets tkUsersTets);

    /**
     * 新增用户信息
     * 
     * @param tkUsersTets 用户信息
     * @return 结果
     */
    public int insertTkUsersTets(TkUsersTets tkUsersTets);

    /**
     * 修改用户信息
     * 
     * @param tkUsersTets 用户信息
     * @return 结果
     */
    public int updateTkUsersTets(TkUsersTets tkUsersTets);

    /**
     * 删除用户信息
     * 
     * @param uid 用户信息主键
     * @return 结果
     */
    public int deleteTkUsersTetsByUid(Long uid);

    /**
     * 批量删除用户信息
     * 
     * @param uids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkUsersTetsByUids(Long[] uids);
}

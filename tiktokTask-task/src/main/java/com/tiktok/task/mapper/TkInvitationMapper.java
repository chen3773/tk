package com.tiktok.task.mapper;


import com.tiktok.task.domain.TkInvitation;
import com.tiktok.task.domain.ov.TeamUserOV;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * 邀请记录Mapper接口
 *
 * @author ruoyi
 * @date 2024-09-23
 */
@Mapper
public interface TkInvitationMapper
{
    /**
     * 查询邀请记录
     *
     * @param id 邀请记录主键
     * @return 邀请记录
     */
    public TkInvitation selectTkInvitationById(Long id);

    /**
     * 查询邀请记录列表
     *
     * @param tkInvitation 邀请记录
     * @return 邀请记录集合
     */
    public List<TkInvitation> selectTkInvitationList(TkInvitation tkInvitation);

    /**
     * 新增邀请记录
     *
     * @param tkInvitation 邀请记录
     * @return 结果
     */
    public int insertTkInvitation(TkInvitation tkInvitation);

    /**
     * 修改邀请记录
     *
     * @param tkInvitation 邀请记录
     * @return 结果
     */
    public int updateTkInvitation(TkInvitation tkInvitation);

    /**
     * 删除邀请记录
     *
     * @param id 邀请记录主键
     * @return 结果
     */
    public int deleteTkInvitationById(Long id);

    /**
     * 批量删除邀请记录
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTkInvitationByIds(Long[] ids);

    List<TeamUserOV> selectTeamUserInfo(TkInvitation tkInvitation);

    HashMap<String,String> getTeamData(Long uid);
}
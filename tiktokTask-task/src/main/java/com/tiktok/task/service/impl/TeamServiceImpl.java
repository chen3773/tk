package com.tiktok.task.service.impl;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.common.utils.SecurityUtils;
import com.tiktok.task.domain.TkInvitation;
import com.tiktok.task.domain.TkUsers;
import com.tiktok.task.domain.ov.TeamUserOV;
import com.tiktok.task.mapper.TkInvitationMapper;
import com.tiktok.task.mapper.TkUsersMapper;
import com.tiktok.task.mapper.TkWallettransactionsMapper;
import com.tiktok.task.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TkInvitationMapper tkInvitationMapper;
    @Autowired
    private TkUsersMapper tkUsersMapper;
    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;

    @Override
    public AjaxResult getTeam(Long level) {
        TkUsers tkUsers = new TkUsers();
        tkUsers.setUsername(SecurityUtils.getUsername());
        TkInvitation tkInvitation = new TkInvitation();
        tkInvitation.setInviterId(tkUsersMapper.selectTkUsersList(tkUsers).get(0).getUid());
        tkInvitation.setLevel(level);
        List<TeamUserOV> tkInvitations = tkInvitationMapper.selectTeamUserInfo(tkInvitation);
        return AjaxResult.success(tkInvitations);
    }

    @Override
    public AjaxResult getTeamData() {
        Long uid = SecurityUtils.getLoginUser().getUser().getUid();
        HashMap<String, String> teamData = tkInvitationMapper.getTeamData(uid);
        //获取出来用户的  amount
        Double amount = tkWallettransactionsMapper.getTotalAmountForTransactionTypeAndUser(uid);
        teamData.put("amount",amount.toString());

        if(teamData==null){
            teamData = new HashMap<>();
            teamData.put("totareward","0");
            teamData.put("amount","0");
            teamData.put("invitations","0");
        }
        return AjaxResult.success(teamData);
    }


}

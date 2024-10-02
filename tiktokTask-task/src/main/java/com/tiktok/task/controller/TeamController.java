package com.tiktok.task.controller;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.common.core.domain.model.LoginBody;
import com.tiktok.task.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

    //用户获取对应层级下级
    @GetMapping("/getTeam")
    public AjaxResult getTeam(Long level){
        AjaxResult res = teamService.getTeam(level);
        return res;
    }

    /**
     * 获取团队总数据
     */

    @GetMapping("/getTeamData")
    public AjaxResult getTeamData(){
        AjaxResult res = teamService.getTeamData();
        return res;
    }


}

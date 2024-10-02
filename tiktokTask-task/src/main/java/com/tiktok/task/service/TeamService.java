package com.tiktok.task.service;

import com.tiktok.common.core.domain.AjaxResult;

public interface TeamService {

    AjaxResult getTeam(Long level);

    AjaxResult getTeamData();
}

package com.tiktok.task.domain.ov;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tiktok.task.domain.TkInvitation;
import com.tiktok.task.domain.TkUsers;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeamUserOV {
    private TkUsers tkUsers;
    private TkInvitation tkInvitation;
}

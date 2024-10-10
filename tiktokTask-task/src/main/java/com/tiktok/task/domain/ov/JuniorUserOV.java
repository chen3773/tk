package com.tiktok.task.domain.ov;

import com.tiktok.common.core.domain.entity.SysUser;
import com.tiktok.task.domain.TkInvitation;
import com.tiktok.task.domain.TkUsers;
import lombok.Data;

@Data
public class JuniorUserOV {
    private TkUsers tkUsers;
    private TkInvitation tkInvitation;
    private SysUser sysUser;
}

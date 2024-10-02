package com.tiktok.task.service.impl;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.task.mapper.AdminMapper;
import com.tiktok.task.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public AjaxResult HomePage() {

        return AjaxResult.success(adminMapper.HomePage());
    }
}

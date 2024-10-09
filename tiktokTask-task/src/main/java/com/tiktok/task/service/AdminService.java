package com.tiktok.task.service;

import com.tiktok.common.core.domain.AjaxResult;

public interface AdminService {
    AjaxResult HomePage();

    AjaxResult AddAndDeduct(String amount,String withdraw,String add,String uid,String rebate);

    AjaxResult TakeOut(Long id);
}

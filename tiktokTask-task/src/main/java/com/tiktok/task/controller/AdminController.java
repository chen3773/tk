package com.tiktok.task.controller;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.task.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping("/getHomePage")
    public AjaxResult HomePage(){
        return  adminService.HomePage();
    }

    /**
     * 给用户加款 或者
     * Add and deduct
     */

    @GetMapping("/AddAndDeduct")
    public AjaxResult AddAndDeduct(String amount,String withdraw,String add,String uid,String rebate){

        return  adminService.AddAndDeduct(amount,withdraw,add,uid,rebate);
    }
    /**
     * 充值错误回滚
     *
     */

    @GetMapping("/TakeOut")
    public AjaxResult TakeOut(Long id){

        return  adminService.TakeOut(id);
    }

}

package com.tiktok.task.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tiktok.common.annotation.Log;
import com.tiktok.common.core.controller.BaseController;
import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.common.enums.BusinessType;
import com.tiktok.task.domain.TkUsersTets;
import com.tiktok.task.service.ITkUsersTetsService;
import com.tiktok.common.utils.poi.ExcelUtil;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2024-10-02
 */
@RestController
@RequestMapping("/task/userstest")
public class TkUsersTetsController extends BaseController
{
    @Autowired
    private ITkUsersTetsService tkUsersTetsService;

    /**
     * 查询用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('task:userstest:list')")
    @GetMapping("/list")
    public AjaxResult list(TkUsersTets tkUsersTets)
    {
        List<TkUsersTets> list = tkUsersTetsService.selectTkUsersTetsList(tkUsersTets);
        return success(list);
    }

    /**
     * 导出用户信息列表
     */
    @PreAuthorize("@ss.hasPermi('task:userstest:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkUsersTets tkUsersTets)
    {
        List<TkUsersTets> list = tkUsersTetsService.selectTkUsersTetsList(tkUsersTets);
        ExcelUtil<TkUsersTets> util = new ExcelUtil<TkUsersTets>(TkUsersTets.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:userstest:query')")
    @GetMapping(value = "/{uid}")
    public AjaxResult getInfo(@PathVariable("uid") Long uid)
    {
        return success(tkUsersTetsService.selectTkUsersTetsByUid(uid));
    }

    /**
     * 新增用户信息
     */
    @PreAuthorize("@ss.hasPermi('task:userstest:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkUsersTets tkUsersTets)
    {
        return toAjax(tkUsersTetsService.insertTkUsersTets(tkUsersTets));
    }

    /**
     * 修改用户信息
     */
    @PreAuthorize("@ss.hasPermi('task:userstest:edit')")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkUsersTets tkUsersTets)
    {
        return toAjax(tkUsersTetsService.updateTkUsersTets(tkUsersTets));
    }

    /**
     * 删除用户信息
     */
    @PreAuthorize("@ss.hasPermi('task:userstest:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{uids}")
    public AjaxResult remove(@PathVariable Long[] uids)
    {
        return toAjax(tkUsersTetsService.deleteTkUsersTetsByUids(uids));
    }
}

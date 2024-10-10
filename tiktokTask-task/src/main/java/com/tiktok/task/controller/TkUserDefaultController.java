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
import com.tiktok.task.domain.TkUserDefault;
import com.tiktok.task.service.ITkUserDefaultService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 系统默认配置Controller
 * 
 * @author ruoyi
 * @date 2024-10-06
 */
@RestController
@RequestMapping("/task/userDefault")
public class TkUserDefaultController extends BaseController
{
    @Autowired
    private ITkUserDefaultService tkUserDefaultService;

    /**
     * 查询系统默认配置列表
     */
    @PreAuthorize("@ss.hasPermi('task:userDefault:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkUserDefault tkUserDefault)
    {
        startPage();
        List<TkUserDefault> list = tkUserDefaultService.selectTkUserDefaultList(tkUserDefault);
        return getDataTable(list);
    }

    /**
     * 查询系统默认配置列表
     */
    @GetMapping("/dispositionList")
    public TableDataInfo dispositionList(TkUserDefault tkUserDefault)
    {
        startPage();
        List<TkUserDefault> list = tkUserDefaultService.selectTkUserDefaultList(tkUserDefault);
        return getDataTable(list);
    }


    /**
     * 导出系统默认配置列表
     */
    @PreAuthorize("@ss.hasPermi('task:userDefault:export')")
    @Log(title = "系统默认配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkUserDefault tkUserDefault)
    {
        List<TkUserDefault> list = tkUserDefaultService.selectTkUserDefaultList(tkUserDefault);
        ExcelUtil<TkUserDefault> util = new ExcelUtil<TkUserDefault>(TkUserDefault.class);
        util.exportExcel(response, list, "系统默认配置数据");
    }

    /**
     * 获取系统默认配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:userDefault:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkUserDefaultService.selectTkUserDefaultById(id));
    }

    /**
     * 新增系统默认配置
     */
    @PreAuthorize("@ss.hasPermi('task:userDefault:add')")
    @Log(title = "系统默认配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkUserDefault tkUserDefault)
    {
        return toAjax(tkUserDefaultService.insertTkUserDefault(tkUserDefault));
    }

    /**
     * 修改系统默认配置
     */
    @PreAuthorize("@ss.hasPermi('task:userDefault:edit')")
    @Log(title = "系统默认配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkUserDefault tkUserDefault)
    {
        return toAjax(tkUserDefaultService.updateTkUserDefault(tkUserDefault));
    }

    /**
     * 删除系统默认配置
     */
    @PreAuthorize("@ss.hasPermi('task:userDefault:remove')")
    @Log(title = "系统默认配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkUserDefaultService.deleteTkUserDefaultByIds(ids));
    }
}

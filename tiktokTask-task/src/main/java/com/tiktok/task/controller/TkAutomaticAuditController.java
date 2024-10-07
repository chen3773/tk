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
import com.tiktok.task.domain.TkAutomaticAudit;
import com.tiktok.task.service.ITkAutomaticAuditService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 自动审核Controller
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
@RestController
@RequestMapping("/task/audit")
public class TkAutomaticAuditController extends BaseController
{
    @Autowired
    private ITkAutomaticAuditService tkAutomaticAuditService;

    /**
     * 查询自动审核列表
     */
    @PreAuthorize("@ss.hasPermi('task:audit:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkAutomaticAudit tkAutomaticAudit)
    {
        startPage();
        List<TkAutomaticAudit> list = tkAutomaticAuditService.selectTkAutomaticAuditList(tkAutomaticAudit);
        return getDataTable(list);
    }

    /**
     * 导出自动审核列表
     */
    @PreAuthorize("@ss.hasPermi('task:audit:export')")
    @Log(title = "自动审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkAutomaticAudit tkAutomaticAudit)
    {
        List<TkAutomaticAudit> list = tkAutomaticAuditService.selectTkAutomaticAuditList(tkAutomaticAudit);
        ExcelUtil<TkAutomaticAudit> util = new ExcelUtil<TkAutomaticAudit>(TkAutomaticAudit.class);
        util.exportExcel(response, list, "自动审核数据");
    }

    /**
     * 获取自动审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:audit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkAutomaticAuditService.selectTkAutomaticAuditById(id));
    }

    /**
     * 新增自动审核
     */
    @PreAuthorize("@ss.hasPermi('task:audit:add')")
    @Log(title = "自动审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkAutomaticAudit tkAutomaticAudit)
    {
        return toAjax(tkAutomaticAuditService.insertTkAutomaticAudit(tkAutomaticAudit));
    }

    /**
     * 修改自动审核
     */
    @PreAuthorize("@ss.hasPermi('task:audit:edit')")
    @Log(title = "自动审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkAutomaticAudit tkAutomaticAudit)
    {
        return toAjax(tkAutomaticAuditService.updateTkAutomaticAudit(tkAutomaticAudit));
    }

    /**
     * 删除自动审核
     */
    @PreAuthorize("@ss.hasPermi('task:audit:remove')")
    @Log(title = "自动审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkAutomaticAuditService.deleteTkAutomaticAuditByIds(ids));
    }
}

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
import com.tiktok.task.domain.TkTaskAcceptances;
import com.tiktok.task.service.ITkTaskAcceptancesService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 用户接取任务Controller
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@RestController
@RequestMapping("/task/acceptances")
public class TkTaskAcceptancesController extends BaseController
{
    @Autowired
    private ITkTaskAcceptancesService tkTaskAcceptancesService;

    /**
     * 查询用户接取任务列表
     */
    @PreAuthorize("@ss.hasPermi('task:acceptances:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkTaskAcceptances tkTaskAcceptances)
    {
        startPage();
        List<TkTaskAcceptances> list = tkTaskAcceptancesService.selectTkTaskAcceptancesList(tkTaskAcceptances);
        return getDataTable(list);
    }

    /**
     * 导出用户接取任务列表
     */
    @PreAuthorize("@ss.hasPermi('task:acceptances:export')")
    @Log(title = "用户接取任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkTaskAcceptances tkTaskAcceptances)
    {
        List<TkTaskAcceptances> list = tkTaskAcceptancesService.selectTkTaskAcceptancesList(tkTaskAcceptances);
        ExcelUtil<TkTaskAcceptances> util = new ExcelUtil<TkTaskAcceptances>(TkTaskAcceptances.class);
        util.exportExcel(response, list, "用户接取任务数据");
    }

    /**
     * 获取用户接取任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:acceptances:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkTaskAcceptancesService.selectTkTaskAcceptancesById(id));
    }

    /**
     * 新增用户接取任务
     */
    @PreAuthorize("@ss.hasPermi('task:acceptances:add')")
    @Log(title = "用户接取任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkTaskAcceptances tkTaskAcceptances)
    {
        return toAjax(tkTaskAcceptancesService.insertTkTaskAcceptances(tkTaskAcceptances));
    }

    /**
     * 修改用户接取任务
     */
    @PreAuthorize("@ss.hasPermi('task:acceptances:edit')")
    @Log(title = "用户接取任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkTaskAcceptances tkTaskAcceptances)
    {
        tkTaskAcceptances.setTips("1");
        return toAjax(tkTaskAcceptancesService.updateTkTaskAcceptances(tkTaskAcceptances));
    }

    /**
     * 删除用户接取任务
     */
    @PreAuthorize("@ss.hasPermi('task:acceptances:remove')")
    @Log(title = "用户接取任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkTaskAcceptancesService.deleteTkTaskAcceptancesByIds(ids));
    }

    /**
     * 审核用户
     */
    @GetMapping("/TaskAudit")
    @PreAuthorize("@ss.hasPermi('task:acceptances:TaskAudit')")
    public AjaxResult TaskAudit(Long id,boolean pass){

        return  tkTaskAcceptancesService.TaskAudit(id,pass);
    }
}

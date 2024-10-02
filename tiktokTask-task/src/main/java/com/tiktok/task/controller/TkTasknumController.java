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
import com.tiktok.task.domain.TkTasknum;
import com.tiktok.task.service.ITkTasknumService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 任务数量Controller
 * 
 * @author ruoyi
 * @date 2024-09-25
 */
@RestController
@RequestMapping("/task/tasknum")
public class TkTasknumController extends BaseController
{
    @Autowired
    private ITkTasknumService tkTasknumService;

    /**
     * 查询任务数量列表
     */
    @PreAuthorize("@ss.hasPermi('task:tasknum:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkTasknum tkTasknum)
    {
        startPage();
        List<TkTasknum> list = tkTasknumService.selectTkTasknumList(tkTasknum);
        return getDataTable(list);
    }

    /**
     * 导出任务数量列表
     */
    @PreAuthorize("@ss.hasPermi('task:tasknum:export')")
    @Log(title = "任务数量", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkTasknum tkTasknum)
    {
        List<TkTasknum> list = tkTasknumService.selectTkTasknumList(tkTasknum);
        ExcelUtil<TkTasknum> util = new ExcelUtil<TkTasknum>(TkTasknum.class);
        util.exportExcel(response, list, "任务数量数据");
    }

    /**
     * 获取任务数量详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:tasknum:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkTasknumService.selectTkTasknumById(id));
    }

    /**
     * 新增任务数量
     */
    @PreAuthorize("@ss.hasPermi('task:tasknum:add')")
    @Log(title = "任务数量", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkTasknum tkTasknum)
    {
        return toAjax(tkTasknumService.insertTkTasknum(tkTasknum));
    }

    /**
     * 修改任务数量
     */
    @PreAuthorize("@ss.hasPermi('task:tasknum:edit')")
    @Log(title = "任务数量", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkTasknum tkTasknum)
    {
        return toAjax(tkTasknumService.updateTkTasknum(tkTasknum));
    }

    /**
     * 删除任务数量
     */
    @PreAuthorize("@ss.hasPermi('task:tasknum:remove')")
    @Log(title = "任务数量", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkTasknumService.deleteTkTasknumByIds(ids));
    }
}

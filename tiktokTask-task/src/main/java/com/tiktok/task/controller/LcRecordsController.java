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
import com.tiktok.task.domain.LcRecords;
import com.tiktok.task.service.ILcRecordsService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 理财分红记录Controller
 * 
 * @author ruoyi
 * @date 2024-10-24
 */
@RestController
@RequestMapping("/task/records")
public class LcRecordsController extends BaseController
{
    @Autowired
    private ILcRecordsService lcRecordsService;

    /**
     * 查询理财分红记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:records:list')")
    @GetMapping("/list")
    public TableDataInfo list(LcRecords lcRecords)
    {
        startPage();
        List<LcRecords> list = lcRecordsService.selectLcRecordsList(lcRecords);
        return getDataTable(list);
    }

    /**
     * 导出理财分红记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:records:export')")
    @Log(title = "理财分红记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LcRecords lcRecords)
    {
        List<LcRecords> list = lcRecordsService.selectLcRecordsList(lcRecords);
        ExcelUtil<LcRecords> util = new ExcelUtil<LcRecords>(LcRecords.class);
        util.exportExcel(response, list, "理财分红记录数据");
    }

    /**
     * 获取理财分红记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:records:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(lcRecordsService.selectLcRecordsById(id));
    }

    /**
     * 新增理财分红记录
     */
    @PreAuthorize("@ss.hasPermi('task:records:add')")
    @Log(title = "理财分红记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LcRecords lcRecords)
    {
        return toAjax(lcRecordsService.insertLcRecords(lcRecords));
    }

    /**
     * 修改理财分红记录
     */
    @PreAuthorize("@ss.hasPermi('task:records:edit')")
    @Log(title = "理财分红记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LcRecords lcRecords)
    {
        return toAjax(lcRecordsService.updateLcRecords(lcRecords));
    }

    /**
     * 删除理财分红记录
     */
    @PreAuthorize("@ss.hasPermi('task:records:remove')")
    @Log(title = "理财分红记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(lcRecordsService.deleteLcRecordsByIds(ids));
    }
}

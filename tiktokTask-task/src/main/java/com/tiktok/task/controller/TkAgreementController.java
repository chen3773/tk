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
import com.tiktok.task.domain.TkAgreement;
import com.tiktok.task.service.ITkAgreementService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 文本Controller
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
@RestController
@RequestMapping("/task/agreement")
public class TkAgreementController extends BaseController
{
    @Autowired
    private ITkAgreementService tkAgreementService;

    /**
     * 查询文本列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TkAgreement tkAgreement)
    {
        startPage();
        List<TkAgreement> list = tkAgreementService.selectTkAgreementList(tkAgreement);
        return getDataTable(list);
    }

    /**
     * 导出文本列表
     */
    @PreAuthorize("@ss.hasPermi('task:agreement:export')")
    @Log(title = "文本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkAgreement tkAgreement)
    {
        List<TkAgreement> list = tkAgreementService.selectTkAgreementList(tkAgreement);
        ExcelUtil<TkAgreement> util = new ExcelUtil<TkAgreement>(TkAgreement.class);
        util.exportExcel(response, list, "文本数据");
    }

    /**
     * 获取文本详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkAgreementService.selectTkAgreementById(id));
    }

    /**
     * 新增文本
     */
    @PreAuthorize("@ss.hasPermi('task:agreement:add')")
    @Log(title = "文本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkAgreement tkAgreement)
    {
        return toAjax(tkAgreementService.insertTkAgreement(tkAgreement));
    }

    /**
     * 修改文本
     */
    @PreAuthorize("@ss.hasPermi('task:agreement:edit')")
    @Log(title = "文本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkAgreement tkAgreement)
    {
        return toAjax(tkAgreementService.updateTkAgreement(tkAgreement));
    }

    /**
     * 删除文本
     */
    @PreAuthorize("@ss.hasPermi('task:agreement:remove')")
    @Log(title = "文本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkAgreementService.deleteTkAgreementByIds(ids));
    }
}

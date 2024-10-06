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
import com.tiktok.task.domain.TkTransactions;
import com.tiktok.task.service.ITkTransactionsService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 上下分日志Controller
 * 
 * @author ruoyi
 * @date 2024-10-05
 */
@RestController
@RequestMapping("/task/transactions")
public class TkTransactionsController extends BaseController
{
    @Autowired
    private ITkTransactionsService tkTransactionsService;

    /**
     * 查询上下分日志列表
     */
    @PreAuthorize("@ss.hasPermi('task:transactions:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkTransactions tkTransactions)
    {
        startPage();
        List<TkTransactions> list = tkTransactionsService.selectTkTransactionsList(tkTransactions);
        return getDataTable(list);
    }

    /**
     * 导出上下分日志列表
     */
    @PreAuthorize("@ss.hasPermi('task:transactions:export')")
    @Log(title = "上下分日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkTransactions tkTransactions)
    {
        List<TkTransactions> list = tkTransactionsService.selectTkTransactionsList(tkTransactions);
        ExcelUtil<TkTransactions> util = new ExcelUtil<TkTransactions>(TkTransactions.class);
        util.exportExcel(response, list, "上下分日志数据");
    }

    /**
     * 获取上下分日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:transactions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkTransactionsService.selectTkTransactionsById(id));
    }

    /**
     * 新增上下分日志
     */
    @PreAuthorize("@ss.hasPermi('task:transactions:add')")
    @Log(title = "上下分日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkTransactions tkTransactions)
    {
        return toAjax(tkTransactionsService.insertTkTransactions(tkTransactions));
    }

    /**
     * 修改上下分日志
     */
    @PreAuthorize("@ss.hasPermi('task:transactions:edit')")
    @Log(title = "上下分日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkTransactions tkTransactions)
    {
        return toAjax(tkTransactionsService.updateTkTransactions(tkTransactions));
    }

    /**
     * 删除上下分日志
     */
    @PreAuthorize("@ss.hasPermi('task:transactions:remove')")
    @Log(title = "上下分日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkTransactionsService.deleteTkTransactionsByIds(ids));
    }



}

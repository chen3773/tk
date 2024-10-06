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
import com.tiktok.task.domain.TkWallettransactions;
import com.tiktok.task.service.ITkWallettransactionsService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 用户钱包交易记录Controller
 * 
 * @author ruoyi
 * @date 2024-10-05
 */
@RestController
@RequestMapping("/task/WalletTransactions")
public class TkWallettransactionsController extends BaseController
{
    @Autowired
    private ITkWallettransactionsService tkWallettransactionsService;

    /**
     * 查询用户钱包交易记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:WalletTransactions:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkWallettransactions tkWallettransactions)
    {
        startPage();
        List<TkWallettransactions> list = tkWallettransactionsService.selectTkWallettransactionsList(tkWallettransactions);
        return getDataTable(list);
    }

    /**
     * 导出用户钱包交易记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:WalletTransactions:export')")
    @Log(title = "用户钱包交易记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkWallettransactions tkWallettransactions)
    {
        List<TkWallettransactions> list = tkWallettransactionsService.selectTkWallettransactionsList(tkWallettransactions);
        ExcelUtil<TkWallettransactions> util = new ExcelUtil<TkWallettransactions>(TkWallettransactions.class);
        util.exportExcel(response, list, "用户钱包交易记录数据");
    }

    /**
     * 获取用户钱包交易记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:WalletTransactions:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkWallettransactionsService.selectTkWallettransactionsById(id));
    }

    /**
     * 新增用户钱包交易记录
     */
    @PreAuthorize("@ss.hasPermi('task:WalletTransactions:add')")
    @Log(title = "用户钱包交易记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkWallettransactions tkWallettransactions)
    {
        return toAjax(tkWallettransactionsService.insertTkWallettransactions(tkWallettransactions));
    }

    /**
     * 修改用户钱包交易记录
     */
    @PreAuthorize("@ss.hasPermi('task:WalletTransactions:edit')")
    @Log(title = "用户钱包交易记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkWallettransactions tkWallettransactions)
    {
        return toAjax(tkWallettransactionsService.updateTkWallettransactions(tkWallettransactions));
    }

    /**
     * 删除用户钱包交易记录
     */
    @PreAuthorize("@ss.hasPermi('task:WalletTransactions:remove')")
    @Log(title = "用户钱包交易记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkWallettransactionsService.deleteTkWallettransactionsByIds(ids));
    }
}

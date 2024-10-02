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
import com.tiktok.task.domain.TkWithdrawals;
import com.tiktok.task.service.ITkWithdrawalsService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 提现记录Controller
 * 
 * @author ruoyi
 * @date 2024-09-26
 */
@RestController
@RequestMapping("/task/Withdrawals")
public class TkWithdrawalsController extends BaseController
{
    @Autowired
    private ITkWithdrawalsService tkWithdrawalsService;

    /**
     * 查询提现记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:Withdrawals:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkWithdrawals tkWithdrawals)
    {
        startPage();
        List<TkWithdrawals> list = tkWithdrawalsService.selectTkWithdrawalsList(tkWithdrawals);
        return getDataTable(list);
    }

    /**
     * 导出提现记录列表
     */
    @PreAuthorize("@ss.hasPermi('task:Withdrawals:export')")
    @Log(title = "提现记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkWithdrawals tkWithdrawals)
    {
        List<TkWithdrawals> list = tkWithdrawalsService.selectTkWithdrawalsList(tkWithdrawals);
        ExcelUtil<TkWithdrawals> util = new ExcelUtil<TkWithdrawals>(TkWithdrawals.class);
        util.exportExcel(response, list, "提现记录数据");
    }

    /**
     * 获取提现记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:Withdrawals:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkWithdrawalsService.selectTkWithdrawalsById(id));
    }

    /**
     * 新增提现记录
     */
    @PreAuthorize("@ss.hasPermi('task:Withdrawals:add')")
    @Log(title = "提现记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkWithdrawals tkWithdrawals)
    {
        return toAjax(tkWithdrawalsService.insertTkWithdrawals(tkWithdrawals));
    }

    /**
     * 修改提现记录
     */
    @PreAuthorize("@ss.hasPermi('task:Withdrawals:edit')")
    @Log(title = "提现记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkWithdrawals tkWithdrawals)
    {
        return toAjax(tkWithdrawalsService.updateTkWithdrawals(tkWithdrawals));
    }

    /**
     * 删除提现记录
     */
    @PreAuthorize("@ss.hasPermi('task:Withdrawals:remove')")
    @Log(title = "提现记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkWithdrawalsService.deleteTkWithdrawalsByIds(ids));
    }
}

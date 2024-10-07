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
import com.tiktok.task.domain.TkBlockchain;
import com.tiktok.task.service.ITkBlockchainService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 区块链名称Controller
 * 
 * @author ruoyi
 * @date 2024-10-07
 */
@RestController
@RequestMapping("/task/blockchain")
public class TkBlockchainController extends BaseController
{
    @Autowired
    private ITkBlockchainService tkBlockchainService;

    /**
     * 查询区块链名称列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TkBlockchain tkBlockchain)
    {
        startPage();
        List<TkBlockchain> list = tkBlockchainService.selectTkBlockchainList(tkBlockchain);
        return getDataTable(list);
    }

    /**
     * 导出区块链名称列表
     */
    @PreAuthorize("@ss.hasPermi('task:blockchain:export')")
    @Log(title = "区块链名称", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkBlockchain tkBlockchain)
    {
        List<TkBlockchain> list = tkBlockchainService.selectTkBlockchainList(tkBlockchain);
        ExcelUtil<TkBlockchain> util = new ExcelUtil<TkBlockchain>(TkBlockchain.class);
        util.exportExcel(response, list, "区块链名称数据");
    }

    /**
     * 获取区块链名称详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:blockchain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkBlockchainService.selectTkBlockchainById(id));
    }

    /**
     * 新增区块链名称
     */
    @PreAuthorize("@ss.hasPermi('task:blockchain:add')")
    @Log(title = "区块链名称", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkBlockchain tkBlockchain)
    {
        return toAjax(tkBlockchainService.insertTkBlockchain(tkBlockchain));
    }

    /**
     * 修改区块链名称
     */
    @PreAuthorize("@ss.hasPermi('task:blockchain:edit')")
    @Log(title = "区块链名称", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkBlockchain tkBlockchain)
    {
        return toAjax(tkBlockchainService.updateTkBlockchain(tkBlockchain));
    }

    /**
     * 删除区块链名称
     */
    @PreAuthorize("@ss.hasPermi('task:blockchain:remove')")
    @Log(title = "区块链名称", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkBlockchainService.deleteTkBlockchainByIds(ids));
    }
}

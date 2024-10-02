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
import com.tiktok.task.domain.TkRoyaltySetting;
import com.tiktok.task.service.ITkRoyaltySettingService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 三级分佣百分比Controller
 * 
 * @author ruoyi
 * @date 2024-09-27
 */
@RestController
@RequestMapping("/task/setting")
public class TkRoyaltySettingController extends BaseController
{
    @Autowired
    private ITkRoyaltySettingService tkRoyaltySettingService;

    /**
     * 查询三级分佣百分比列表
     */
    @PreAuthorize("@ss.hasPermi('task:setting:list')")
    @GetMapping("/list")
    public TableDataInfo list(TkRoyaltySetting tkRoyaltySetting)
    {
        startPage();
        List<TkRoyaltySetting> list = tkRoyaltySettingService.selectTkRoyaltySettingList(tkRoyaltySetting);
        return getDataTable(list);
    }

    /**
     * 导出三级分佣百分比列表
     */
    @PreAuthorize("@ss.hasPermi('task:setting:export')")
    @Log(title = "三级分佣百分比", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkRoyaltySetting tkRoyaltySetting)
    {
        List<TkRoyaltySetting> list = tkRoyaltySettingService.selectTkRoyaltySettingList(tkRoyaltySetting);
        ExcelUtil<TkRoyaltySetting> util = new ExcelUtil<TkRoyaltySetting>(TkRoyaltySetting.class);
        util.exportExcel(response, list, "三级分佣百分比数据");
    }

    /**
     * 获取三级分佣百分比详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:setting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkRoyaltySettingService.selectTkRoyaltySettingById(id));
    }

    /**
     * 新增三级分佣百分比
     */
    @PreAuthorize("@ss.hasPermi('task:setting:add')")
    @Log(title = "三级分佣百分比", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkRoyaltySetting tkRoyaltySetting)
    {
        return toAjax(tkRoyaltySettingService.insertTkRoyaltySetting(tkRoyaltySetting));
    }

    /**
     * 修改三级分佣百分比
     */
    @PreAuthorize("@ss.hasPermi('task:setting:edit')")
    @Log(title = "三级分佣百分比", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkRoyaltySetting tkRoyaltySetting)
    {
        return toAjax(tkRoyaltySettingService.updateTkRoyaltySetting(tkRoyaltySetting));
    }

    /**
     * 删除三级分佣百分比
     */
    @PreAuthorize("@ss.hasPermi('task:setting:remove')")
    @Log(title = "三级分佣百分比", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkRoyaltySettingService.deleteTkRoyaltySettingByIds(ids));
    }
}

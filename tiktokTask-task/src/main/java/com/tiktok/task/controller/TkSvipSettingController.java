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
import com.tiktok.task.domain.TkSvipSetting;
import com.tiktok.task.service.ITkSvipSettingService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * svip默认配置Controller
 * 
 * @author ruoyi
 * @date 2024-09-29
 */
@RestController
@RequestMapping("/task/svipSetting")
public class TkSvipSettingController extends BaseController
{
    @Autowired
    private ITkSvipSettingService tkSvipSettingService;

    /**
     * 查询svip默认配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TkSvipSetting tkSvipSetting)
    {
        startPage();
        List<TkSvipSetting> list = tkSvipSettingService.selectTkSvipSettingList(tkSvipSetting);
        return getDataTable(list);
    }

    /**
     * 导出svip默认配置列表
     */
    @PreAuthorize("@ss.hasPermi('task:svipSetting:export')")
    @Log(title = "svip默认配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TkSvipSetting tkSvipSetting)
    {
        List<TkSvipSetting> list = tkSvipSettingService.selectTkSvipSettingList(tkSvipSetting);
        ExcelUtil<TkSvipSetting> util = new ExcelUtil<TkSvipSetting>(TkSvipSetting.class);
        util.exportExcel(response, list, "svip默认配置数据");
    }

    /**
     * 获取svip默认配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:svipSetting:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tkSvipSettingService.selectTkSvipSettingById(id));
    }

    /**
     * 新增svip默认配置
     */
    @PreAuthorize("@ss.hasPermi('task:svipSetting:add')")
    @Log(title = "svip默认配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TkSvipSetting tkSvipSetting)
    {
        return toAjax(tkSvipSettingService.insertTkSvipSetting(tkSvipSetting));
    }

    /**
     * 修改svip默认配置
     */
    @PreAuthorize("@ss.hasPermi('task:svipSetting:edit')")
    @Log(title = "svip默认配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TkSvipSetting tkSvipSetting)
    {
        return toAjax(tkSvipSettingService.updateTkSvipSetting(tkSvipSetting));
    }

    /**
     * 删除svip默认配置
     */
    @PreAuthorize("@ss.hasPermi('task:svipSetting:remove')")
    @Log(title = "svip默认配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tkSvipSettingService.deleteTkSvipSettingByIds(ids));
    }

    /**
     *
     * 给客户升级SVIP
     */

    @GetMapping("/UpgradeSvip")
    public AjaxResult UpgradeSvip(Long uid,Long lv)
    {
        return toAjax(tkSvipSettingService.UpgradeSvip(uid,lv));
    }
}

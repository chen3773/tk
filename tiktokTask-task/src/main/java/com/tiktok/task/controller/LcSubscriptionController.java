package com.tiktok.task.controller;

import java.util.List;
import java.util.Map;
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
import com.tiktok.task.domain.LcSubscription;
import com.tiktok.task.service.ILcSubscriptionService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 认购Controller
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/task/subscription")
public class LcSubscriptionController extends BaseController
{
    @Autowired
    private ILcSubscriptionService lcSubscriptionService;

    /**
     * 查询认购列表
     */
    @PreAuthorize("@ss.hasPermi('task:subscription:list')")
    @GetMapping("/list")
    public TableDataInfo list(LcSubscription lcSubscription)
    {
        startPage();
        List<LcSubscription> list = lcSubscriptionService.selectLcSubscriptionList(lcSubscription);
        return getDataTable(list);
    }

    /**
     * 导出认购列表
     */
    @PreAuthorize("@ss.hasPermi('task:subscription:export')")
    @Log(title = "认购", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LcSubscription lcSubscription)
    {
        List<LcSubscription> list = lcSubscriptionService.selectLcSubscriptionList(lcSubscription);
        ExcelUtil<LcSubscription> util = new ExcelUtil<LcSubscription>(LcSubscription.class);
        util.exportExcel(response, list, "认购数据");
    }

    /**
     * 获取认购详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:subscription:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(lcSubscriptionService.selectLcSubscriptionById(id));
    }

    /**
     * 新增认购
     */
    @PreAuthorize("@ss.hasPermi('task:subscription:add')")
    @Log(title = "认购", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LcSubscription lcSubscription)
    {
        return toAjax(lcSubscriptionService.insertLcSubscription(lcSubscription));
    }

    /**
     * 修改认购
     */
    @PreAuthorize("@ss.hasPermi('task:subscription:edit')")
    @Log(title = "认购", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LcSubscription lcSubscription)
    {
        return toAjax(lcSubscriptionService.updateLcSubscription(lcSubscription));
    }

    /**
     * 删除认购
     */
    @PreAuthorize("@ss.hasPermi('task:subscription:remove')")
    @Log(title = "认购", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(lcSubscriptionService.deleteLcSubscriptionByIds(ids));
    }

    /**
     * 用户认购
     */
    @PostMapping("/Buy")
    public AjaxResult Buy(@RequestBody Map<String, Object> requestData)
    {
        return lcSubscriptionService.Buy(requestData);
    }

    /**
     * 用户查询购买的产品列表
     */
    @GetMapping("/MyProduct")
    public AjaxResult MyProduct(String status)
    {
        return lcSubscriptionService.MyProduct(status);
    }


    //订单号获取出订单详情和订单的分红记录
    @GetMapping("/orderDtails")
    public AjaxResult orderDtails(String orderNumber)
    {
        return lcSubscriptionService.orderDtails(orderNumber);
    }

    /**
     * 获取出订单数量和金额
     */

    @GetMapping("/getProjectCountAndAmount")
    public AjaxResult getProjectCountAndAmount()
    {
        return lcSubscriptionService.getProjectCountAndAmount();
    }
}

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
import com.tiktok.task.domain.LcProduct;
import com.tiktok.task.service.ILcProductService;
import com.tiktok.common.utils.poi.ExcelUtil;
import com.tiktok.common.core.page.TableDataInfo;

/**
 * 众筹产品Controller
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
@RestController
@RequestMapping("/task/product")
public class LcProductController extends BaseController
{
    @Autowired
    private ILcProductService lcProductService;

    /**
     * 查询众筹产品列表
     */
    @PreAuthorize("@ss.hasPermi('task:product:list')")
    @GetMapping("/list")
    public TableDataInfo list(LcProduct lcProduct)
    {
        startPage();
        List<LcProduct> list = lcProductService.selectLcProductList(lcProduct);
        return getDataTable(list);
    }


    /**
     * 查询众筹产品列表  --客户端
     */
    @GetMapping("/ProductList")
    public TableDataInfo ProductList(LcProduct lcProduct)
    {
        startPage();
        List<LcProduct> list = lcProductService.UserSelectLcProductList(lcProduct);
        return getDataTable(list);
    }


    /**
     * 获取众筹产品详细信息
     */
    @GetMapping(value = "/getProductInfo")
    public AjaxResult getProductInfo(Long productId)
    {
        return success(lcProductService.selectLcProductByProductId(productId));
    }



    /**
     * 导出众筹产品列表
     */
    @PreAuthorize("@ss.hasPermi('task:product:export')")
    @Log(title = "众筹产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, LcProduct lcProduct)
    {
        List<LcProduct> list = lcProductService.selectLcProductList(lcProduct);
        ExcelUtil<LcProduct> util = new ExcelUtil<LcProduct>(LcProduct.class);
        util.exportExcel(response, list, "众筹产品数据");
    }

    /**
     * 获取众筹产品详细信息
     */
    @PreAuthorize("@ss.hasPermi('task:product:query')")
    @GetMapping(value = "/{productId}")
    public AjaxResult getInfo(@PathVariable("productId") Long productId)
    {
        return success(lcProductService.selectLcProductByProductId(productId));
    }

    /**
     * 新增众筹产品
     */
    @PreAuthorize("@ss.hasPermi('task:product:add')")
    @Log(title = "众筹产品", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody LcProduct lcProduct)
    {
        return toAjax(lcProductService.insertLcProduct(lcProduct));
    }

    /**
     * 修改众筹产品
     */
    @PreAuthorize("@ss.hasPermi('task:product:edit')")
    @Log(title = "众筹产品", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody LcProduct lcProduct)
    {
        return toAjax(lcProductService.updateLcProduct(lcProduct));
    }

    /**
     * 删除众筹产品
     */
    @PreAuthorize("@ss.hasPermi('task:product:remove')")
    @Log(title = "众筹产品", businessType = BusinessType.DELETE)
	@DeleteMapping("/{productIds}")
    public AjaxResult remove(@PathVariable Long[] productIds)
    {
        return toAjax(lcProductService.deleteLcProductByProductIds(productIds));
    }
}

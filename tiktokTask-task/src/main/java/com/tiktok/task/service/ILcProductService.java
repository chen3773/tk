package com.tiktok.task.service;

import java.util.List;
import com.tiktok.task.domain.LcProduct;

/**
 * 众筹产品Service接口
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
public interface ILcProductService 
{
    /**
     * 查询众筹产品
     * 
     * @param productId 众筹产品主键
     * @return 众筹产品
     */
    public LcProduct selectLcProductByProductId(Long productId);

    /**
     * 查询众筹产品列表
     * 
     * @param lcProduct 众筹产品
     * @return 众筹产品集合
     */
    public List<LcProduct> selectLcProductList(LcProduct lcProduct);

    /**
     * 新增众筹产品
     * 
     * @param lcProduct 众筹产品
     * @return 结果
     */
    public int insertLcProduct(LcProduct lcProduct);

    /**
     * 修改众筹产品
     * 
     * @param lcProduct 众筹产品
     * @return 结果
     */
    public int updateLcProduct(LcProduct lcProduct);

    /**
     * 批量删除众筹产品
     * 
     * @param productIds 需要删除的众筹产品主键集合
     * @return 结果
     */
    public int deleteLcProductByProductIds(Long[] productIds);

    /**
     * 删除众筹产品信息
     * 
     * @param productId 众筹产品主键
     * @return 结果
     */
    public int deleteLcProductByProductId(Long productId);

    List<LcProduct> UserSelectLcProductList(LcProduct lcProduct);
}

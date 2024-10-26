package com.tiktok.task.service.impl;

import java.math.BigDecimal;
import java.util.List;
import com.tiktok.common.utils.DateUtils;
import com.tiktok.task.util.PercentageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tiktok.task.mapper.LcProductMapper;
import com.tiktok.task.domain.LcProduct;
import com.tiktok.task.service.ILcProductService;

/**
 * 众筹产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
@Service
public class LcProductServiceImpl implements ILcProductService 
{
    @Autowired
    private LcProductMapper lcProductMapper;

    /**
     * 查询众筹产品
     * 
     * @param productId 众筹产品主键
     * @return 众筹产品
     */
    @Override
    public LcProduct selectLcProductByProductId(Long productId)
    {
        LcProduct lcProduct = lcProductMapper.selectLcProductByProductId(productId);
        lcProduct.setGrossAmount((new BigDecimal(lcProduct.getTotalShares()).multiply(lcProduct.getTotalAmount())).toString());
        double v = PercentageCalculator.calculatePercentage(lcProduct.getTotalShares().intValue(), lcProduct.getSoldShares().intValue());
        lcProduct.setCompleted(v+"%");
        return lcProduct;
    }

    /**
     * 查询众筹产品列表
     * 
     * @param lcProduct 众筹产品
     * @return 众筹产品
     */
    @Override
    public List<LcProduct> selectLcProductList(LcProduct lcProduct)
    {
        return lcProductMapper.selectLcProductList(lcProduct);
    }

    /**
     * 新增众筹产品
     * 
     * @param lcProduct 众筹产品
     * @return 结果
     */
    @Override
    public int insertLcProduct(LcProduct lcProduct)
    {
        lcProduct.setCreateTime(DateUtils.getNowDate());
        return lcProductMapper.insertLcProduct(lcProduct);
    }

    /**
     * 修改众筹产品
     * 
     * @param lcProduct 众筹产品
     * @return 结果
     */
    @Override
    public int updateLcProduct(LcProduct lcProduct)
    {
        lcProduct.setUpdateTime(DateUtils.getNowDate());
        return lcProductMapper.updateLcProduct(lcProduct);
    }

    /**
     * 批量删除众筹产品
     * 
     * @param productIds 需要删除的众筹产品主键
     * @return 结果
     */
    @Override
    public int deleteLcProductByProductIds(Long[] productIds)
    {
        return lcProductMapper.deleteLcProductByProductIds(productIds);
    }

    /**
     * 删除众筹产品信息
     * 
     * @param productId 众筹产品主键
     * @return 结果
     */
    @Override
    public int deleteLcProductByProductId(Long productId)
    {
        return lcProductMapper.deleteLcProductByProductId(productId);
    }

    @Override
    public List<LcProduct> UserSelectLcProductList(LcProduct lcProduct) {
        LcProduct newlcProduct =new LcProduct();
        newlcProduct.setDeleted("0");
        newlcProduct.setType(lcProduct.getType());
        List<LcProduct> lcProducts = lcProductMapper.UserSelectLcProductList(lcProduct);
        for (int i = 0; i < lcProducts.size(); i++) {
            Long totalShares = lcProducts.get(i).getTotalShares();//份额数量
            Long soldShares = lcProducts.get(i).getSoldShares();//售出数量
            BigDecimal totalAmount = lcProducts.get(i).getTotalAmount();//单价
            double v = PercentageCalculator.calculatePercentage(totalShares.intValue(), soldShares.intValue());
            lcProducts.get(i).setCompleted(v+"%");
            lcProducts.get(i).setGrossAmount((new BigDecimal(totalShares).multiply(totalAmount)).toString());
        }
        return lcProducts;
    }
}

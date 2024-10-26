package com.tiktok.task.service;

import java.util.List;
import java.util.Map;

import com.tiktok.common.core.domain.AjaxResult;
import com.tiktok.task.domain.LcSubscription;

/**
 * 认购Service接口
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
public interface ILcSubscriptionService 
{
    /**
     * 查询认购
     * 
     * @param id 认购主键
     * @return 认购
     */
    public LcSubscription selectLcSubscriptionById(Long id);

    /**
     * 查询认购列表
     * 
     * @param lcSubscription 认购
     * @return 认购集合
     */
    public List<LcSubscription> selectLcSubscriptionList(LcSubscription lcSubscription);

    /**
     * 新增认购
     * 
     * @param lcSubscription 认购
     * @return 结果
     */
    public int insertLcSubscription(LcSubscription lcSubscription);

    /**
     * 修改认购
     * 
     * @param lcSubscription 认购
     * @return 结果
     */
    public int updateLcSubscription(LcSubscription lcSubscription);

    /**
     * 批量删除认购
     * 
     * @param ids 需要删除的认购主键集合
     * @return 结果
     */
    public int deleteLcSubscriptionByIds(Long[] ids);

    /**
     * 删除认购信息
     * 
     * @param id 认购主键
     * @return 结果
     */
    public int deleteLcSubscriptionById(Long id);

    AjaxResult Buy(Map<String, Object> requestData);

    AjaxResult MyProduct(String status);

    AjaxResult orderDtails(String orderNumber);

    AjaxResult getProjectCountAndAmount();
}

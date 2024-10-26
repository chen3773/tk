package com.tiktok.task.mapper;

import java.util.List;
import com.tiktok.task.domain.LcSubscription;
import com.tiktok.task.domain.ov.SubscriptionProductOV;
import org.apache.ibatis.annotations.Param;

/**
 * 认购Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
public interface LcSubscriptionMapper 
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
    public List<LcSubscription> listOfDividends();
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
     * 删除认购
     * 
     * @param id 认购主键
     * @return 结果
     */
    public int deleteLcSubscriptionById(Long id);

    /**
     * 批量删除认购
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLcSubscriptionByIds(Long[] ids);

    List<SubscriptionProductOV> selectSubscriptionProductByUserIdAndStatus(@Param("userId") Long userId, @Param("status") String status,
                                                                           @Param("orderNumber") String orderNumber);

}

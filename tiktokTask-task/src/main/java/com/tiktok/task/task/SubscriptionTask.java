package com.tiktok.task.task;

import com.tiktok.task.domain.*;
import com.tiktok.task.mapper.*;
import com.tiktok.task.util.OrderNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Component("SubscriptionTask")
public class SubscriptionTask {
    @Autowired
    private LcSubscriptionMapper lcSubscriptionMapper;
    @Autowired
    private TkUsersMapper tkUsersMapper;
    @Autowired
    private LcProductMapper lcProductMapper;
    @Autowired
    private LcRecordsMapper lcRecordsMapper;
    @Autowired
    private TkWallettransactionsMapper tkWallettransactionsMapper;

    /**
     * 获取出所有未结束的产品进行分红
     */

    public void dividend()
    {

        List<LcSubscription> lcSubscriptions = lcSubscriptionMapper.listOfDividends();
        for (int i = 0; i < lcSubscriptions.size(); i++) {
            LcSubscription lcSubscription1 = lcSubscriptions.get(i);
            Long userId = lcSubscription1.getUserId(); //用户id
            Long productId = lcSubscription1.getProductId();//产品id
            String orderNumber = lcSubscription1.getOrderNumber();
            BigDecimal quantity = new BigDecimal(lcSubscription1.getSubscriptionCount());//认购数量
            Long dividendIntervalDays = lcSubscription1.getDividendIntervalDays();//间隔天数
            BigDecimal multiply = new BigDecimal(lcSubscription1.getPurchasePrice())
                    .multiply(quantity);//交易总价

            //获取出产品和用户信息
            TkUsers tkUsers = tkUsersMapper.selectTkUsersByUid(userId);//用户信息
            LcProduct lcProduct = lcProductMapper.selectLcProductByProductId(productId);//产品信息
            //天数减1
            Integer num1 = Integer.parseInt(lcSubscription1.getRemainingDays()) - 1;
            long num2 = lcSubscription1.getDividendDays() - 1;
            lcSubscription1.setRemainingDays(num1.toString());
            lcSubscription1.setDividendDays(num2);
            lcSubscriptionMapper.updateLcSubscription(lcSubscription1);



            if(num2 ==0){//分红时间到了
                //重置次数分红
                lcSubscription1.setDividendDays(dividendIntervalDays);
                lcSubscriptionMapper.updateLcSubscription(lcSubscription1);
                //这个地方要区分质押和分红
                String s = new BigDecimal(lcProduct.getReturnRate()).multiply(quantity).toString();//本次分红金额
                if(lcProduct.getType().equals("0")){ //质押
                //只添加记录  结束才改变余额
                    LcRecords lcRecords = new LcRecords();
                    lcRecords.setAmount(s);
                    lcRecords.setProductId(lcProduct.getProductId());
                    lcRecords.setUserId(userId);
                    lcRecords.setTitle("Revenue");
                    lcRecords.setType("0");
                    lcRecords.setStatus("0");
                    lcRecords.setTimestamp(new Date());
                    lcRecords.setOrderNumber(orderNumber);
                    lcRecordsMapper.insertLcRecords(lcRecords);
                }else if(lcProduct.getType().equals("1")){//分红
                    LcRecords lcRecords = new LcRecords();
                    lcRecords.setAmount(s);
                    lcRecords.setProductId(lcProduct.getProductId());
                    lcRecords.setUserId(userId);
                    lcRecords.setTitle("Revenue");
                    lcRecords.setType("0");
                    lcRecords.setStatus("1");
                    lcRecords.setTimestamp(new Date());
                    lcRecords.setOrderNumber(orderNumber);
                    lcRecordsMapper.insertLcRecords(lcRecords);


                    //返还用户钱
                    String amount = new BigDecimal(tkUsers.getInvestmentAmount()).add(new BigDecimal(s)).toString();
                    tkUsers.setInvestmentAmount(amount);
                    tkUsersMapper.updateTkUsers(tkUsers);

                    //添加钱包记录
                    TkWallettransactions tkWallettransaction = new TkWallettransactions();
                    tkWallettransaction.setUserid(userId);
                    tkWallettransaction.setFundBalance(new BigDecimal(amount));
                    tkWallettransaction.setCategory("income");
                    tkWallettransaction.setDescription("Product income"+"#"+lcProduct.getProductId());
                    tkWallettransaction.setAmount(new BigDecimal(s));
                    tkWallettransaction.setTransactionStatus("已完成");
                    tkWallettransaction.setUpdatedAt(new Date());
                    tkWallettransaction.setTransactionType("withdraw");
                    tkWallettransaction.setTransactionDate(new Date());
                    tkWallettransaction.setOrderNumber(OrderNumberGenerator.generateOrderNumber());
                    tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransaction);
                }
            }
            //订单结束
            if(lcSubscription1.getRemainingDays().equals("0")){ //订单结束  返还本金
                LcRecords lcRecords = new LcRecords();
                lcRecords.setAmount(multiply.toString());
                lcRecords.setProductId(lcProduct.getProductId());
                lcRecords.setUserId(userId);
                lcRecords.setTitle("Refund");
                lcRecords.setType("0");
                lcRecords.setStatus("1");
                lcRecords.setTimestamp(new Date());
                lcRecords.setOrderNumber(orderNumber);
                lcRecordsMapper.insertLcRecords(lcRecords);
                lcSubscription1.setStatus("1");
                //修改状态为结束
                lcSubscriptionMapper.updateLcSubscription(lcSubscription1);

                //获取出来历史返现记录
                LcRecords lcRecords1 = new LcRecords();
                lcRecords1.setOrderNumber(orderNumber);
                lcRecords1.setTitle("Revenue");
                List<LcRecords> lcRecords2 = lcRecordsMapper.selectLcRecordsList(lcRecords1);

                lcRecords1.setTitle("Refund");
                List<LcRecords> refundAmount = lcRecordsMapper.selectLcRecordsList(lcRecords1);
                //把返现累加起来
                BigDecimal money = new BigDecimal(0);
                money = money.add(new BigDecimal(refundAmount.get(0).getAmount()));
                if(lcProduct.getType().equals("0")){//质押

                    for (int i1 = 0; i1 < lcRecords2.size(); i1++) {
                        BigDecimal bigDecimal = new BigDecimal(lcRecords2.get(i1).getAmount());
                        money =  money.add(bigDecimal);
                    }

                    //返还用户钱
                    String amount = new BigDecimal(tkUsers.getInvestmentAmount()).add(money).toString();
                    tkUsers.setInvestmentAmount(amount);
                    tkUsersMapper.updateTkUsers(tkUsers);

                    //添加钱包记录
                    TkWallettransactions tkWallettransaction = new TkWallettransactions();
                    tkWallettransaction.setUserid(userId);
                    tkWallettransaction.setFundBalance(new BigDecimal(amount));
                    tkWallettransaction.setCategory("income");
                    tkWallettransaction.setDescription("Product income"+"#"+lcProduct.getProductId());
                    tkWallettransaction.setAmount(money);
                    tkWallettransaction.setTransactionStatus("已完成");
                    tkWallettransaction.setUpdatedAt(new Date());
                    tkWallettransaction.setTransactionType("withdraw");
                    tkWallettransaction.setTransactionDate(new Date());
                    tkWallettransaction.setOrderNumber(OrderNumberGenerator.generateOrderNumber());
                    tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransaction);
                }else if(lcProduct.getType().equals("1")){
                    //返还用户钱
                    String amount = new BigDecimal(tkUsers.getInvestmentAmount()).add(money).toString();
                    tkUsers.setInvestmentAmount(amount);
                    tkUsersMapper.updateTkUsers(tkUsers);

                    //添加钱包记录
                    TkWallettransactions tkWallettransaction = new TkWallettransactions();
                    tkWallettransaction.setUserid(userId);
                    tkWallettransaction.setFundBalance(new BigDecimal(amount));
                    tkWallettransaction.setCategory("income");
                    tkWallettransaction.setDescription("Product income"+"#"+lcProduct.getProductId());
                    tkWallettransaction.setAmount(money);
                    tkWallettransaction.setTransactionStatus("已完成");
                    tkWallettransaction.setUpdatedAt(new Date());
                    tkWallettransaction.setTransactionType("withdraw");
                    tkWallettransaction.setTransactionDate(new Date());
                    tkWallettransaction.setOrderNumber(OrderNumberGenerator.generateOrderNumber());
                    tkWallettransactionsMapper.insertTkWallettransactions(tkWallettransaction);
                }
            }
        }
    }
}

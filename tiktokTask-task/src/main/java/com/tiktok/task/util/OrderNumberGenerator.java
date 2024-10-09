package com.tiktok.task.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class OrderNumberGenerator {
    private static final Random random = new Random();

    public static String generateOrderNumber() {
        // 获取当前时间的时间戳
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 生成一个随机的三位数
        int randomNum = random.nextInt(900) + 100; // 100到999之间的随机数
        // 拼接时间戳和随机数
        return timestamp + randomNum;
    }

    public static void main(String[] args) {
        String orderNumber = generateOrderNumber();
        System.out.println("生成的订单号: " + orderNumber);
    }
}

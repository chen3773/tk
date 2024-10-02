package com.tiktok.task.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SVIPNotificationGenerator {

    // 前缀部分
    private static final String[] PREFIXES = {
            "Happy", "Blue", "Green", "Fast", "Lazy", "Brave", "Shiny", "Clever", "Bright", "Crazy"
    };

    // 后缀部分
    private static final String[] SUFFIXES = {
            "Cat", "Dog", "Bird", "Fish", "Bear", "Fox", "Tiger", "Eagle", "Monkey", "Wolf"
    };

    // 会员等级
    private static final String[] LEVELS = {
            "SVIP1", "SVIP2", "SVIP3", "SVIP4", "SVIP5"
    };

    // 用来生成随机的用户名
    public static String generateRandomUsername() {
        Random random = new Random();
        String prefix = PREFIXES[random.nextInt(PREFIXES.length)];
        String suffix = SUFFIXES[random.nextInt(SUFFIXES.length)];
        return prefix + suffix; // 组合前缀和后缀生成用户名
    }

    // 隐藏用户名的中间部分
    public static String hideUsername(String username) {
        int len = username.length();
        if (len <= 4) {
            return username; // 如果用户名太短，不做处理
        }
        // 获取用户名的前1/4部分和后1/4部分
        String visibleStart = username.substring(0, len / 4);
        String visibleEnd = username.substring(3 * len / 4);
        return visibleStart + "****" + visibleEnd; // 中间部分用****代替
    }

    // 生成随机的升级消息
    public static String generateRandomMessage() {
        Random random = new Random();
        // 随机生成用户名
        String username = generateRandomUsername();
        // 隐藏用户名部分
        String hiddenUsername = hideUsername(username);
        // 随机选择一个会员等级
        String level = LEVELS[random.nextInt(LEVELS.length)];
        // 返回随机生成的提示信息
        return "User " + hiddenUsername + " upgraded to " + level;
    }

    // 生成多个随机消息，避免重复用户名
    public static List<String> generateRandomMessages(int count) {
        Set<String> uniqueUsernames = new HashSet<>();
        List<String> messages = new ArrayList<>();
        while (messages.size() < count) {
            String message = generateRandomMessage();
            String username = message.split(" ")[1].replace("****", ""); // 去掉隐藏符号，确保唯一性
            if (!uniqueUsernames.contains(username)) { // 确保用户名唯一
                uniqueUsernames.add(username);
                messages.add(message);
            }
        }
        return messages;
    }

    // 主方法用于测试
    public static void main(String[] args) {
        List<String> notifications = generateRandomMessages(5); // 生成 5 条消息
        for (String message : notifications) {
            System.out.println(message);
        }
    }
}

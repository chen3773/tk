package com.tiktok.task.util;

import java.security.SecureRandom;

public class InviteCodeGenerator {

    // 可用字符集合（排除了0、O、I、L）
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private static final int CODE_LENGTH = 6;
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 生成六位数邀请码
     * @return 六位数邀请码
     */
    public static String generateInviteCode() {
        StringBuilder inviteCode = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            inviteCode.append(CHARACTERS.charAt(index));
        }
        return inviteCode.toString();
    }

    public static void main(String[] args) {
        // 测试生成邀请码
        for (int i = 0; i < 10; i++) {
            System.out.println(generateInviteCode());
        }
    }
}

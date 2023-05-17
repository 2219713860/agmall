package com.zhangxu.agmall.utils;

import org.springframework.stereotype.Component;

/**
 * 创建一个生成验证码的工具类，生成随机的验证码。
 */
@Component
public class VerifyCodeUtils {

    public String generateVerifyCode(int length) {
        String verifyCode = "";
        for (int i = 0; i < length; i++) {
            verifyCode += (int) (Math.random() * 10);
        }
        return verifyCode;
    }
}
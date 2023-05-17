package com.zhangxu.agmall.service;

import org.springframework.stereotype.Service;

@Service
public class VerifyCodeService {

    public boolean verifyCode(String inputCode, String verifyCode) {
        return inputCode.equals(verifyCode);
    }
}
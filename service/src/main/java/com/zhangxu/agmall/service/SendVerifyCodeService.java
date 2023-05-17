package com.zhangxu.agmall.service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.zhangxu.agmall.utils.MailUtils;
import com.zhangxu.agmall.utils.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendVerifyCodeService {

    @Autowired
    private MailUtils mailUtils;

    @Autowired
    private VerifyCodeUtils verifyCodeUtils;

    public void sendVerifyCode(String to) throws MessagingException {
        String verifyCode = verifyCodeUtils.generateVerifyCode(6);
        String subject = "验证码";
        String content = "您的验证码是：" + verifyCode + "，请在5分钟内完成验证。";
        try {
            mailUtils.sendMail(to, subject, content);
        } catch (javax.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
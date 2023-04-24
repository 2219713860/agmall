package com.zhangxu.agmall.service.timingtask;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

/**
 * @author zhangxu
 * @create 2023-04-19
 */
public class MyPayConfig implements WXPayConfig {
        @Override
        public String getAppID() {
            return "wx632c8f211f8122c6";
        }

        @Override
        public String getMchID() {
            return "1497984412";
        }

        @Override
        public String getKey() {
            return "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
        }

        @Override
        public InputStream getCertStream() {
            return null;
        }

        @Override
        public int getHttpConnectTimeoutMs() {
            return 0;
        }

        @Override
        public int getHttpReadTimeoutMs() {
            return 0;
        }
}

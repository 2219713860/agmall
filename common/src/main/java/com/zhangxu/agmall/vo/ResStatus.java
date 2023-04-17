package com.zhangxu.agmall.vo;

/**
 * @author zhangxu
 * @create 2023-04-07
 */
public class ResStatus {
   public static final int OK=10000;
   public static final int NO=10001;
   public static final int LOGIN_FAIL=20002;//登录已过期
   public static final int NOT_LOGIN=20001;//用户未登录
   public static final int TOKEN_ILLEGALITY=20003;//token不合法

}

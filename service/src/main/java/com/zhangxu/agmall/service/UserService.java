package com.zhangxu.agmall.service;

import com.zhangxu.agmall.service.entity.User;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
public interface UserService {
    User selectUser(String userId);
}

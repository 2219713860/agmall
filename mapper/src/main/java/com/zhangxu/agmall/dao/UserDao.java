package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.service.entity.User;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
public interface UserDao {
    User selectUser(String userId);
}

package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.User;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
public interface UserDao {
    /**
     * 用户注册
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param userId
     * @return
     */
    User queryUserByName(String username);
}

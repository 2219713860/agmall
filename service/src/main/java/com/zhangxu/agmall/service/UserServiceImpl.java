package com.zhangxu.agmall.service;

import com.zhangxu.agmall.dao.UserDao;
import com.zhangxu.agmall.service.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserDao userDao;

    @Override
    public User selectUser(String userId) {
        User user = userDao.selectUser(userId);
        return user;
    }
}

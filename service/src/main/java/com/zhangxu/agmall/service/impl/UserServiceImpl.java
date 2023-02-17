package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.UserDao;
import com.zhangxu.agmall.entity.User;
import com.zhangxu.agmall.service.UserService;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public ResultVO selectUser(String username, String password) {
        User user = userDao.queryUserByName(username);
        //todo 没有情况判断
        ResultVO resultVO = new ResultVO(200, "成功", user);
        return resultVO;
    }

    @Override
    public ResultVO registUser(User user) {
        int insertUser = userDao.insertUser(user);
        //todo  没有加密 情况判断
        ResultVO resultVO = new ResultVO(200,"成功",user);
        return resultVO;
    }
}

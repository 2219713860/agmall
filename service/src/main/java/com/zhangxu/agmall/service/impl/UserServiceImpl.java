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

    /**
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultVO loginUser(String username, String password) {
        //todo 没有情况判断
        if (username==null){//确认用户名和密码都输入了
            return new ResultVO(200,"请输入用户名",null);
        }
        if(password==null){
            return new ResultVO(200,"请输入密码",null);
        }
        User user = userDao.queryUserByName(username);
        //确认用户查到了
        if (user==null) {
            return new ResultVO(200, "用户不存在", null);
        }
//        对比密码
        if (password.equals(user.getPassword())) {
           return new ResultVO(200, "成功", user);
        }
//        密码错误返回密码错误信息
        return new ResultVO(200, "密码错误", null);
    }

    @Override
    public ResultVO registUser(User user) {
        int insertUser = userDao.insertUser(user);
        //todo  没有加密 情况判断
        ResultVO resultVO = new ResultVO(200,"成功",user);
        return resultVO;
    }
}

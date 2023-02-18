package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.UserDao;
import com.zhangxu.agmall.entity.User;
import com.zhangxu.agmall.service.UserService;
import com.zhangxu.agmall.utils.MD5Utils;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
@Service
@Scope(name = "singleton", description = "其实spring管理的默认就是单例")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Transactional //事务开启，事务默认的级别是可重复读
    public ResultVO registUser(String username, String password) {
        synchronized (this) {
//第一：查询数据库里用户是否已经存在
            User user = userDao.queryUserByName(username);
//第二：如果没有注册，就进行保存操作
            if (user == null) {
                user =new User();
                //todo 当用户输入用户就提示是否注册可行
             password = MD5Utils.md5(password);
                user.setUsername(username);
                user.setPassword(password);
                user.setUserImg("img/defualt.img");
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                int userNum = userDao.insertUser(user);
                System.out.println("========================");
                System.out.println(user);
                System.out.println("========================");
                if (userNum >= 1) {
                    return new ResultVO(200, "注册成功", user);
                } else {
                    return new ResultVO(200, "注册失败，数据库异常，请几分钟后重试", user);
                }
            } else {
                //todo 当用户输入用户就提示是否注册可行
                return new ResultVO(200, "注册失败，用户已存在", null);
            }
        }
    }

    /**
     * @param username
     * @param password
     * @return
     */
    @Override
    public ResultVO loginUser(String username, String password) {
        //todo 没有情况判断
        if (username == null) {//确认用户名和密码都输入了
            return new ResultVO(200, "请输入用户名", null);
        }
        if (password == null) {
            return new ResultVO(200, "请输入密码", null);
        }
        User user = userDao.queryUserByName(username);
        //确认用户查到了
        if (user == null) {
            return new ResultVO(200, "用户不存在", null);
        }
//        对比密码
        //todo 缺少对传入的密码进行MD5加密，密码加密后才能与数据库密码对上
        password = MD5Utils.md5(password);
        if (password.equals(user.getPassword())) {
            return new ResultVO(200, "成功", user);
        }
//        密码错误返回密码错误信息
        return new ResultVO(200, "密码错误", null);
    }
}

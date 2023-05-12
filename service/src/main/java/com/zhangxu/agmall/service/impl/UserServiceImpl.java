package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.UsersMapper;
import com.zhangxu.agmall.service.UserService;
import com.zhangxu.agmall.utils.MD5Utils;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import com.zhangxu.agmall.entity.Users;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
@Service
@Scope(name = "singleton", description = "其实spring管理的默认就是单例")
public class UserServiceImpl implements UserService {
    @Resource
    private UsersMapper usersMapper;

    @Transactional //事务开启，事务默认的级别是可重复读
    public ResultVO registUser(String username, String password) {
        synchronized (this) {
//第一：查询数据库里用户是否已经存在
            Example usersExample = new Example(Users.class);
            Example.Criteria criteria = usersExample.createCriteria();
            criteria.andEqualTo("username", username);
            List<Users> users = usersMapper.selectByExample(usersExample);
            //todo 当用户输入用户就提示是否注册可行
            if (users.size() == 0) {
                Users user = new Users();
                //todo 当用户输入用户就提示是否注册可行
                password = MD5Utils.md5(password);
                user.setUsername(username);
                user.setPassword(password);
                user.setUserImg("imgs/default.png");
                user.setUserRegtime(new Date());
                user.setUserModtime(new Date());
                user.setIsAdmin(0);
                int userNum = usersMapper.insert(user);
                System.out.println("========================");
                System.out.println(user);
                System.out.println("========================");
                if (userNum >= 1) {
                    return new ResultVO(ResStatus.OK, "注册成功", user);
                } else {
                    return new ResultVO(ResStatus.NO, "注册失败，数据库异常，请几分钟后重试", user);
                }
            } else return new ResultVO(ResStatus.NO, "注册失败，用户已存在", null);

            //第二：如果没有注册，就进行保存操作
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
        Example example = new Example(Users.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username", username);
        List<Users> users = usersMapper.selectByExample(example);
        //确认用户查到了
        if (users.size() == 0) {
            System.out.println(username + ":" + password);
            return new ResultVO(ResStatus.NO, "用户不存在", null);
        }
//        对比密码
        Users user = users.get(0);
        //todo 缺少对传入的密码进行MD5加密，密码加密后才能与数据库密码对上
        if (user.getPassword() != null) {
            password = MD5Utils.md5(password);
            if (password.equals(user.getPassword())) {
                System.out.println(user);
                JwtBuilder builder = Jwts.builder();
                HashMap<String, Object> map = new HashMap<>();
                map.put("key1",new Date(System.currentTimeMillis()));
                map.put("key2",new Date(System.currentTimeMillis()+24*60*60));
                String token = builder
                        .setSubject(username).//主题，一般使用username
                        setIssuedAt(new Date()).//token生成时间
                        setId(user.getUserId()+"").//设置用户id为token的ID
                        setClaims(map).//一般用来存储用户的权限信息
                        setExpiration(new Date(System.currentTimeMillis()+72*60*60*1000)).//token有效日期
                        signWith(SignatureAlgorithm.HS256,"123456").//使用什么“加密算法”以及加密的“密码”
                        compact();
                return new ResultVO(ResStatus.OK, token, user);
            }
        } else {
            return new ResultVO(ResStatus.NO, "存储密码查询出错，请等待几分钟后重试", null);
        }
//        密码错误返回密码错误信息
        return new ResultVO(ResStatus.NO, "密码错误", null);
    }
}

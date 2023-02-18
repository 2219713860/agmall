package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author zhangxu
 * @create 2023-02-18
 */
@SpringBootTest("com.zhangxu.agmall.ApiApplicationTests")
@RunWith(SpringRunner.class)
public class UserDaoTest {
    @Resource
    UserDao userDao;
    @Test
    public void queryUserByNameTest() {
        User zhangsan = userDao.queryUserByName("zhangsan");
        System.out.println(zhangsan);
    }
}
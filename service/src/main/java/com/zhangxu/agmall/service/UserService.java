package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.User;
import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
public interface UserService {
    ResultVO loginUser(String username,String password);
    ResultVO registUser(String username,String password);
}

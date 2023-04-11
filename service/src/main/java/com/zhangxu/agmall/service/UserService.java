package com.zhangxu.agmall.service;

import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.stereotype.Repository;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
@Repository
public interface UserService {
    ResultVO loginUser(String username,String password);
    ResultVO registUser(String username,String password);
}

package com.zhangxu.agmall.service;

import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-04-17
 */
public interface UserAddrService {
    public ResultVO listUserAddrByUserId(int userId);
}

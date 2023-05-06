package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.UserAddr;
import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-04-17
 */
public interface UserAddrService {
    public ResultVO listUserAddrByUserId(int userId);
    public ResultVO modifyAddrByAddrId(UserAddr userAddr);
    public ResultVO deleteAddrByAddrId(String addrId);
    public ResultVO AddrByAddrId(UserAddr userAddr);


}

package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.UserAddrMapper;
import com.zhangxu.agmall.entity.UserAddr;
import com.zhangxu.agmall.service.UserAddrService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author zhangxu
 * &#064;create  2023-04-17
 */
@Service
public class UserAddrServiceImpl implements UserAddrService {
    @Autowired
    private UserAddrMapper userAddrMapper;
    @Transactional(propagation = Propagation.SUPPORTS)//带有增删改的操作使用REQUIRED
    public ResultVO listUserAddrByUserId(int userId) {
        Example example = new Example(UserAddr.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userId);//传递的是实体类的属性，""内的也是实体的属性
        criteria.andEqualTo("status",1);//传递的是实体类的属性，""内的也是实体的属性
        List<UserAddr> userAddrs = userAddrMapper.selectByExample(example);
        if (userAddrs.size()>0){
        return new ResultVO(ResStatus.OK,"success",userAddrs);
        }
        return new ResultVO(ResStatus.NO,"fail",null);
    }

    @Override
    public ResultVO modifyAddrByAddrId(UserAddr userAddr) {
        userAddr.setUpdateTime(new Date());
        int i = userAddrMapper.updateByPrimaryKeySelective(userAddr);
        if (i>0){
            //
            return new ResultVO(ResStatus.OK,"success",null);
        }
            return new ResultVO(ResStatus.NO,"fail",null);
    }

    @Override
    public ResultVO deleteAddrByAddrId(String addrId) {
        int i = userAddrMapper.deleteByPrimaryKey(addrId);
        if (i>0){
            //
            return new ResultVO(ResStatus.OK,"success",null);
        }
        return new ResultVO(ResStatus.NO,"fail",null);
    }
    @Override
    public ResultVO AddrByAddrId(UserAddr userAddr) {
        userAddr.setCreateTime(new Date());
        userAddr.setUpdateTime(new Date());
        String make = RandomString.make(10);
        userAddr.setAddrId(make);
        userAddr.setCommonAddr(0);
        userAddr.setStatus(1);
        int i = userAddrMapper.insert(userAddr);
        if (i>0){
            //
            return new ResultVO(ResStatus.OK,"success",null);
        }
        return new ResultVO(ResStatus.NO,"fail",null);
    }

}

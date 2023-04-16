package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.ShoppingCartMapper;
import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.service.ShoppingCartService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zhangxu
 * @create 2023-04-15
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    @Override
    public ResultVO addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setCartTime(sdf.format(new Date()));
        int insert = shoppingCartMapper.insert(shoppingCart);
        if(insert>0){
            return new ResultVO(ResStatus.OK, "success", null);
        }
            return new ResultVO(ResStatus.NO, "fail", null);
    }
}

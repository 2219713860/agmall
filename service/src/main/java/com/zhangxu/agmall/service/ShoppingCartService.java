package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-04-15
 */
public interface ShoppingCartService{
    public ResultVO addShoppingCart(ShoppingCart shoppingCart);
}

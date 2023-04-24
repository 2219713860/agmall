package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.vo.ResultVO;

import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-15
 */
public interface ShoppingCartService {
    public ResultVO addShoppingCart(ShoppingCart shoppingCart);

    public ResultVO listShoppingCartsByUserId(Integer userId);

    public ResultVO updateCartNum(int cartId, int cartNum);

    public ResultVO listShoppingCartCids(String cidsString);
    public ResultVO deleteShoppingCartByCid(int cartId);
}

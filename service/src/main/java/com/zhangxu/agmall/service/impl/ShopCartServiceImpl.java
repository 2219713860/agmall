package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.ShoppingCartMapper;
import com.zhangxu.agmall.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxu
 * @create 2023-04-10
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    public void testt(){
        String string = shoppingCartMapper.toString();
        System.out.println(string);
    }


}

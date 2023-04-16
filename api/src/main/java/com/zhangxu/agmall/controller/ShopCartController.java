package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.service.ShoppingCartService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxu
 * @create 2023-04-08
 */
@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Api(value = "提供购物车业务相关接口", tags = "购物车管理")
public class ShopCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResultVO listCarts(@RequestBody ShoppingCart shoppingCart
            , @RequestHeader("token") String token) {
        ResultVO resultVO = shoppingCartService.addShoppingCart(shoppingCart);
        return resultVO;
    }
}
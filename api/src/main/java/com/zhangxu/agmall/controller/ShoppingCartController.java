package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.service.ShoppingCartService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-08
 */
@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Api(value = "提供购物车业务相关接口", tags = "购物车管理")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResultVO addCart(@RequestBody ShoppingCart shoppingCart
            , @RequestHeader("token") String token) {
        ResultVO resultVO = shoppingCartService.addShoppingCart(shoppingCart);
        return resultVO;
    }

    @GetMapping("/list")
    public ResultVO listCarts(Integer userId, @RequestHeader("token") String token) {
        if (userId != null) {
            return shoppingCartService.listShoppingCartsByUserId(userId);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @PutMapping("/update/{cid}/{cnum}")
    public ResultVO updateNum(
            @PathVariable("cid") Integer cid,
            @PathVariable("cnum") Integer cnum,
            @RequestHeader("token") String token
    ) {
        if (cid != null) {
            return shoppingCartService.updateCartNum(cid, cnum);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }
    @GetMapping("/choosed-list-bycids")
    public ResultVO listCids(String cids, @RequestHeader("token") String token) {
        if (cids != null) {
            return shoppingCartService.listShoppingCartCids(cids);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @DeleteMapping("/delete-cid")
    public ResultVO deleteByCid(String cid,@RequestHeader("token") String token) {
        if (cid!=null) {
            int cidInt = Integer.parseInt(cid);
            return shoppingCartService.deleteShoppingCartByCid(cidInt);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }
}
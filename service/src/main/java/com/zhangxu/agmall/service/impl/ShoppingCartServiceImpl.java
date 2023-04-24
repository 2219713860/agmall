package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.ShoppingCartMapper;
import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.entity.ShoppingCartVO;
import com.zhangxu.agmall.service.ShoppingCartService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangxu
 * @create 2023-04-15
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public ResultVO addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.setCartTime(sdf.format(new Date()));
        int insert = shoppingCartMapper.insert(shoppingCart);
        if (insert > 0) {
            return new ResultVO(ResStatus.OK, "success", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ResultVO listShoppingCartsByUserId(Integer userId) {
        List<ShoppingCartVO> shoppingCartVOS = shoppingCartMapper.selectShoppingCartByUserId(userId);
        if (shoppingCartVOS.size() > 0) {
            return new ResultVO(ResStatus.OK, "success", shoppingCartVOS);
        }
        return new ResultVO(ResStatus.OK, "空空如也", null);
    }

    @Override
    public ResultVO updateCartNum(int cartId, int cartNum) {
        int updatedNumber = shoppingCartMapper.updateCartNumByCartId(cartId, cartNum);
        if (updatedNumber > 0) {
            return new ResultVO(ResStatus.OK, "success", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @Override
    public ResultVO listShoppingCartCids(String cidsString) {
        String[] splitString = cidsString.split(",");
        ArrayList<Integer> cids = new ArrayList<>();
        for (int i = 0; i < splitString.length; i++) {
            cids.add(Integer.parseInt(splitString[i]));
        }
        List<ShoppingCartVO> shoppingCartVOS = shoppingCartMapper.selectShoppingCartByCids(cids);
        if (shoppingCartVOS.size() > 0) {
            return new ResultVO(ResStatus.OK, "success", shoppingCartVOS);
        }
        return new ResultVO(ResStatus.NO, "fail", shoppingCartVOS);
    }
}

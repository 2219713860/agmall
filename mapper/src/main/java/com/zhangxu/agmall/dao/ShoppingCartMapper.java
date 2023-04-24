package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.entity.ShoppingCartVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartMapper extends GeneralDAO<ShoppingCart>{
        public List<ShoppingCartVO> selectShoppingCartByUserId(@Param("userId") int userId);
        public int updateCartNumByCartId(@Param("cartId") int cartId,
                                         @Param( "cartNum" ) int cartNum);
        public List<ShoppingCartVO> selectShoppingCartByCids(List<Integer> cids);

}
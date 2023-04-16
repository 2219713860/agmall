package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.ShoppingCart;
import com.zhangxu.agmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.MySqlMapper;

@Repository
public interface ShoppingCartMapper extends GeneralDAO<ShoppingCart>{
}
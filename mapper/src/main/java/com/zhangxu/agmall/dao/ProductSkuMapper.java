package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.ProductSku;
import com.zhangxu.agmall.entity.ProductVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSkuMapper extends GeneralDAO<ProductSku> {
    public List<ProductSku> selectLowestPriceByProductId(String productId);
}
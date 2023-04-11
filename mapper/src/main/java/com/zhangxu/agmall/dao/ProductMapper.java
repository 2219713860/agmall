package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.Product;
import com.zhangxu.agmall.entity.ProductVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends GeneralDAO<Product> {
    public List<ProductVO> selectRecommendProducts();
}
package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.ProductImg;
import com.zhangxu.agmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImgMapper extends GeneralDAO<ProductImg> {
    public List<ProductImg> selectProductImgByProductId(int productId);
}
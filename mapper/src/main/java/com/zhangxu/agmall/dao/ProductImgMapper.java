package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.ProductImg;
import com.zhangxu.agmall.general.GeneralDAO;

import java.util.List;

public interface ProductImgMapper extends GeneralDAO<ProductImg> {
    public List<ProductImg> selectProductImgByProductId(int productId);

}
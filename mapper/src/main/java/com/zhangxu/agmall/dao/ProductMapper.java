package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.Product;
import com.zhangxu.agmall.entity.ProductVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends GeneralDAO<Product> {
//    推荐商品
    public List<ProductVO> selectRecommendProducts();
//    查询指定一级分类下商品销量最高的六个商品
    public List<ProductVO> selectTop6ByCategory(Integer categoryId);
}
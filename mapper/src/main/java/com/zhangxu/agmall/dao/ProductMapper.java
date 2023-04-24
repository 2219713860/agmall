package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.Product;
import com.zhangxu.agmall.entity.ProductVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper extends GeneralDAO<Product> {
    //    推荐商品
    public List<ProductVO> selectRecommendProducts();

    //    查询指定一级分类下商品销量最高的六个商品
    public List<ProductVO> selectTop6ByCategory(Integer categoryId);

    /**
     *
     * @param cid Category表中的三级分类ID
     * @param start 数据的起始位置
     * @param limit 数据的记录数
     * @return
     */
    public List<ProductVO> selectProductByCategoryId(@Param("cid") int cid,
                                                     @Param("start") int start,
                                                     @Param("limit") int limit);

    public List<String> selectBrandByCategoryId(@Param("cid") int cid);

    public List<ProductVO> selectProductByKeyword(@Param("keyword") String keyword,
                                                  @Param("start") int start,
                                                  @Param("limit") int limit);

    /**
     * 根据搜索键字查询相关商品的品牌列表
     *
     * */
    public List<String> selectBrandByKeyword(String keyword);


}
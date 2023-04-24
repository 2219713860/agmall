package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.ProductVO;
import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-04-11
 */
public interface ProductService {

    public ResultVO listRecommendProducts();

    public ResultVO getProductBaseInfo(String productId);

    public ResultVO getProductParamsById(String productId);

    public ResultVO getProductsByCategoryId(int categoryId,int pageNum,int limit);

    public ResultVO listBrands(int cid);

    public ResultVO searchProduct(String keyword,int start,int limit);

    public ResultVO listBrands(String keyword);


}

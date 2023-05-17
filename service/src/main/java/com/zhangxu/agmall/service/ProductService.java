package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.Product;
import com.zhangxu.agmall.entity.ProductParams;
import com.zhangxu.agmall.entity.ProductVO;
import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-04-11
 */
public interface ProductService {

    public ResultVO listRecommendProducts();

    public ResultVO getProductBaseInfo(String productId);

    public ResultVO getProductParamsById(String paramId);//

    public ResultVO getProductById(String productId);//

    public ResultVO getProductImgById(String productId);//

    public ResultVO getProductSkuById(String productId);//

    public ResultVO getProductsByCategoryId(int categoryId, int pageNum, int limit);

    public ResultVO listBrands(int cid);

    public ResultVO searchProduct(String keyword, int start, int limit);

    public ResultVO listBrands(String keyword);

    public ResultVO updateProductById(Product product);

    public ResultVO updateProductParamById(ProductParams productParams);

    public ResultVO getProductParamByPid(String paramId);

    public ResultVO addProduct(Product product);

    public ResultVO deleteProduct(String productId);

    public ResultVO addProductParam(ProductParams productParam);

    public ResultVO deleteParam(String paramId);
}

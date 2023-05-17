package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.ProductSku;
import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-05-15
 */
public interface ProductSkusService {

    public ResultVO selectOneSku(String skuId);
    public ResultVO modifySku(ProductSku productSku);
    public ResultVO addSku(ProductSku productSku);
    public ResultVO deleteSku(String skuId);
}

package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.Product;
import com.zhangxu.agmall.entity.ProductImg;
import com.zhangxu.agmall.entity.ProductSku;
import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-05-15
 */
public interface ProductImgService {

    public ResultVO selectOneImg(String id);
    public ResultVO modifyImg(ProductImg productImg);
    public ResultVO addImg(ProductImg productImg);
    public ResultVO deleteImg(String id);
}

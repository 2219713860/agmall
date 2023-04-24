package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.ProductComments;
import com.zhangxu.agmall.vo.ResultVO;

import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-14
 */
public interface ProductCommentService {
    public ResultVO listCommentsByproduct(String productId,int pageNum, int limit);

    public ResultVO getCommentsCountByProductId(String productId);

    public ResultVO addCommentByOrderItemList(List<ProductComments> productCommentsList,String orderId);

}

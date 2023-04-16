package com.zhangxu.agmall.service;

import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-04-14
 */
public interface ProductCommentService {
    public ResultVO listCommentsByproduct(String productId,int pageNum, int limit);

    public ResultVO getCommentsCountByProductId(String productId);

}

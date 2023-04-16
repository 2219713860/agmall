package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.ProductComments;
import com.zhangxu.agmall.general.GeneralDAO;
import com.zhangxu.agmall.entity.ProductCommentsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCommentsMapper extends GeneralDAO<ProductComments> {
    public List<ProductCommentsVO> selectCommontsByProductId(@Param("productId") String productId
                                                            ,@Param("start") int start
                                                            ,@Param("limit") int limit);


}

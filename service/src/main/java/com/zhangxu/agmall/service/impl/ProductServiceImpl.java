package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.ProductMapper;
import com.zhangxu.agmall.entity.ProductVO;
import com.zhangxu.agmall.service.ProductService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-11
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public ResultVO listRecommendProducts() {
        List<ProductVO> productVOS = productMapper.selectRecommendProducts();
        return new ResultVO(ResStatus.OK,"success",productVOS);
    }
}

package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.ProductService;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxu
 * @create 2023-04-13
 */
@RestController
@CrossOrigin
@RequestMapping("/product")
@Api(value = "提供商品相关的接口", tags = "商品管理")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/detail-info/{productId}")
    @ApiOperation("商品基本信息查询接口")
    public ResultVO getProductBasicInfo(@PathVariable("productId") String pid) {
        ResultVO productBaseInfo = productService.getProductBaseInfo(pid);
        return productBaseInfo;
    }
    @GetMapping("/detail-params/{productId}")
    @ApiOperation("商品参数信息查询接口")
    public ResultVO getProductParamsInfo(@PathVariable("productId") String pid) {
        ResultVO productParamsById = productService.getProductParamsById(pid);

        return productParamsById;
    }
}

package com.zhangxu.agmall.controller;

import com.google.gson.Gson;
import com.zhangxu.agmall.entity.ProductImg;
import com.zhangxu.agmall.entity.ProductSku;
import com.zhangxu.agmall.service.ProductSkusService;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author zhangxu
 * @create 2023-05-15
 */
@RestController
@CrossOrigin
@RequestMapping("/productSku")
@ApiOperation("商品套餐管理")
public class ProductSkuController {
    @Autowired
    private ProductSkusService productSkusService;

    @GetMapping("/getOneSku")
    @ApiOperation("根据商品套餐主键查询套餐")
    public ResultVO getOneSku(@RequestHeader("token") String token, String skuId) {
        ResultVO resultVO = productSkusService.selectOneSku(skuId);
        return resultVO;
    }

    @PutMapping("/modifySku")
    @ApiOperation("根据主键更新商品套餐")
    public ResultVO modifyOneSku(@RequestHeader("token") String token, @RequestBody ProductSku productSku) {
        productSku.setUpdateTime(new Date());
        ResultVO resultVO = productSkusService.modifySku(productSku);
        return resultVO;
    }

    @PostMapping("/addSku")
    @ApiOperation("新增套餐")
    public ResultVO insertOneSku(@RequestHeader("token") String token,
                                 @RequestBody ProductSku productSku) {
        ResultVO resultVO = productSkusService.addSku(productSku);
        return resultVO;
    }

    @DeleteMapping("/deleteSku")
    @ApiOperation("删除套餐")
    public ResultVO deleteSku(@RequestHeader("token")String token,String skuId) {
        ResultVO resultVO = productSkusService.deleteSku(skuId);
        return resultVO;
    }
}

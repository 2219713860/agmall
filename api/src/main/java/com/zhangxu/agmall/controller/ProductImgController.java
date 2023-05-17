package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.entity.ProductImg;
import com.zhangxu.agmall.service.ProductImgService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangxu
 * @create 2023-05-15
 */
@RestController
@CrossOrigin
@RequestMapping("/productImg")
@ApiOperation("商品图像管理")
public class ProductImgController {
    @Autowired
    private ProductImgService productImgService;

    @GetMapping("/getOneImg")
    @ApiOperation("根据Id查询商品图片")
    public ResultVO getOneImg(@RequestHeader("token") String token, String id) {
        ResultVO resultVO = productImgService.selectOneImg(id);
        return resultVO;
    }
    @PutMapping("/modifyImg")
    @ApiOperation("根据主键更新图片")
    public ResultVO modifyOneImg(@RequestHeader("token") String token,@RequestBody ProductImg productImg) {
        ResultVO resultVO = productImgService.modifyImg(productImg);
        return resultVO;
    }
    @PostMapping("/addImg")
    @ApiOperation("新增图片")
    public ResultVO insertOneImg(@RequestHeader("token") String token,@RequestBody ProductImg productImg) {
        ResultVO resultVO = productImgService.addImg(productImg);
        return resultVO;
    }
    @DeleteMapping("/deleteImg")
    @ApiOperation("删除图片")
    public ResultVO deleteImg(@RequestHeader("token")String token,String id) {
        ResultVO resultVO = productImgService.deleteImg(id);
        return resultVO;
    }
}

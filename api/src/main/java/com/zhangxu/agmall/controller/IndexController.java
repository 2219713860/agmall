package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.CategoryService;
import com.zhangxu.agmall.service.IndexImgService;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;

/**
 * @author zhangxu
 * @create 2023-04-09
 */
@RestController
@CrossOrigin
@RequestMapping("/index")
@Api(value = "提供首页数据显示所需的接口",tags ="首页管理" )
public class IndexController {
    @Autowired
    private IndexImgService indexImgService;

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/indeximg")
    @ApiOperation("首页轮播图接口")
    public ResultVO listIndexImgs(){
        ResultVO resultVO = indexImgService.listIndexImg();
        return resultVO;
    }

//    @GetMapping("/category-list")
//    @ApiOperation("首页商品分类接口")
//    public ResultVO listCategory(){
//        ResultVO resultVO = categoryService.listCategory();
//        return resultVO;
//    }
    @GetMapping("/category-list")
    @ApiOperation("首页商品分类接口")
    public ResultVO listCategory(@RequestParam("parentId") String parentId) {
        int parsedInt = Integer.parseInt(parentId);
        ResultVO resultVO = categoryService.listCategory2(parsedInt);
        return resultVO;
    }
}

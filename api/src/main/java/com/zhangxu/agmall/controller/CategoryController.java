package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.CategoryService;
import com.zhangxu.agmall.vo.ResultVO;
import io.jsonwebtoken.lang.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangxu
 * @create 2023-04-10
 */
@RestController
@RequestMapping("/category")
@Api
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    @ApiOperation("使用内连接查询商品分类")
    public ResultVO listCategory(){
        ResultVO resultVO = categoryService.listCategory();
        return resultVO;
    }
    @GetMapping("/categories2")
    @ApiOperation("使用子查询查询商品分类，A集<A>,用递。方法中,调方法")
    public ResultVO listCategory2(@RequestParam("parentId") String IdString){
        int parentId = Integer.parseInt(IdString);
        return categoryService.listCategory2(parentId);
    }
}

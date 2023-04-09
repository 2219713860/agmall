package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.IndexImgService;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/indeximg")
    @ApiOperation("首页轮播图接口")
    public ResultVO listIndexImgs(){
        ResultVO resultVO = indexImgService.listIndexImg();
        return resultVO;
    }
}

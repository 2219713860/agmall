package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.entity.ProductComments;
import com.zhangxu.agmall.service.ProductCommentService;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-29
 */
@CrossOrigin
@RestController
@RequestMapping("/productcomments")
public class ProductCommentsController {

    @Autowired
    private ProductCommentService productCommentService;

    @PostMapping("/add-commentsByOrderItemID")
    public ResultVO addComments(List<ProductComments> productCommentsList,String orderId){
        ResultVO resultVO = productCommentService.addCommentByOrderItemList(productCommentsList, orderId);
        return resultVO;
    }
}

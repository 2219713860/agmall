package com.zhangxu.agmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {


    private String productId;
    private String productName;
    private Integer categoryId;
    private Integer rootCategoryId;
    private Integer soldNum;
    private Integer productStatus;
    private String content;
    private Date createTime;
    private Date updateTime;
    private List<ProductImg> imgs;//查询商品的时候，关联查询商品图片的信息
    private List<ProductSku> skus;//查询商品的时候，关联查询商品套产的信息
}
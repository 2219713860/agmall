package com.zhangxu.agmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartVO {
    private Integer cartId;
    private String productId;
    private String skuId;
    private String userId;
    private String cartNum;
    private String cartTime;
    private BigDecimal productPrice;
    private String skuProps;

    // 购物车列表数据
    private String productName;//商品名称
    private String productImg;//商品图片

//    套餐表中的数据
    private String sellPrice;
    private String originalPrice;
    private String skuName;
    private int skuStock;//商品库存
}
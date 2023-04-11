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
    /**
     * 商品主键id
     */
    private List<ProductImg> imgs;


    @Id
    @Column(name = "product_id")
    private String productId;

    /**
     * 商品名称;商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 分类外键id;分类id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 一级分类外键id;一级分类id，用于优化查询
     */
    @Column(name = "root_category_id")
    private Integer rootCategoryId;

    /**
     * 销量;累计销售
     */
    @Column(name = "sold_num")
    private Integer soldNum;

    /**
     * 默认是1，表示正常状态, -1表示删除, 0下架;默认是1，表示正常状态, -1表示删除, 0下架
     */
    @Column(name = "product_status")
    private Integer productStatus;

    /**
     * 商品内容;商品内容
     */
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;
}
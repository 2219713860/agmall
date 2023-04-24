package com.zhangxu.agmall.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageHelper<T> {
    private int count;//总记录数
    private int pageCount;//总页数
    private List<T> list;//数据集合
}

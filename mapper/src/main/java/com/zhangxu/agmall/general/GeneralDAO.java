package com.zhangxu.agmall.general;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author zhangxu
 * @create 2023-02-26
 */
public interface GeneralDAO<T> extends Mapper<T>, MySqlMapper<T> {
}

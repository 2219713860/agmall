package com.zhangxu.agmall.service;

import com.zhangxu.agmall.vo.ResultVO;

/**
 * @author zhangxu
 * @create 2023-04-10
 */
public interface CategoryService {
    public ResultVO listCategory();
    public ResultVO listCategory2(Integer parentId);
}

package com.zhangxu.agmall.service;

import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.stereotype.Repository;

/**
 * @author zhangxu
 * @create 2023-04-10
 */
@Repository
public interface CategoryService {
    //查询一级分类二级三级分类列表
    public ResultVO listCategory();

    //查询一级分类以及到最后一层分类的分类列表
    public ResultVO listCategory2(Integer parentId);

    public ResultVO listFirstLevelCategories();
}

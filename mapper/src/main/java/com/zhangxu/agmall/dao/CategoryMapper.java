package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.Category;
import com.zhangxu.agmall.entity.CategoryVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
public interface CategoryMapper extends GeneralDAO<Category> {

    public ArrayList<CategoryVO> selectAllCategories();
    public ArrayList<CategoryVO> selectAllCategories2(@Param("parentId") Integer parentID);
}
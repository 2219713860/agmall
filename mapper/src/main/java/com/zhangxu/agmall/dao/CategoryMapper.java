package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.Category;
import com.zhangxu.agmall.entity.CategoryVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
public interface CategoryMapper extends GeneralDAO<Category> {

    //连接查询
    public ArrayList<CategoryVO> selectAllCategories();
//    子查询
    public ArrayList<CategoryVO> selectAllCategories2(@Param("parentId") Integer parentID);
//    查询一级类别
    public List<CategoryVO> selectFirstLevelCategories();
}
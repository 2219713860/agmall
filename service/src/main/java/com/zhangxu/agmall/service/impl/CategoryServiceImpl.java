package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.CategoryMapper;
import com.zhangxu.agmall.entity.CategoryVO;
import com.zhangxu.agmall.service.CategoryService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-10
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public ResultVO listCategory() {
        ArrayList<CategoryVO> categoryVOS = categoryMapper.selectAllCategories();
        ResultVO resultVO = new ResultVO(ResStatus.OK, "三级分类", categoryVOS);
        for (CategoryVO categoryVO : categoryVOS) {
            System.out.println(categoryVO);
        }
        return resultVO;
    }
    public ResultVO listCategory2(Integer parentId){

        ArrayList<CategoryVO> categoryVOS = categoryMapper.selectAllCategories2(parentId);
        return new ResultVO(ResStatus.OK,"success",categoryVOS);
    }

}

package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.IndexImg;
import com.zhangxu.agmall.general.GeneralDAO;

import java.util.List;

public interface IndexImgMapper extends GeneralDAO<IndexImg> {
    public List<IndexImg> listIndexImg();
}
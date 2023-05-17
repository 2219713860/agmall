package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.ProductImgMapper;
import com.zhangxu.agmall.dao.ProductMapper;
import com.zhangxu.agmall.dao.ProductSkuMapper;
import com.zhangxu.agmall.entity.ProductImg;
import com.zhangxu.agmall.entity.ProductSku;
import com.zhangxu.agmall.service.ProductImgService;
import com.zhangxu.agmall.service.ProductSkusService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * @author zhangxu
 * @create 2023-05-15
 */
@Service
public class ProductImgServiceImpl implements ProductImgService {
    @Autowired
    private ProductImgMapper productImgMapper;

    @Override
    public ResultVO selectOneImg(String id) {
        ProductImg productImg = productImgMapper.selectByPrimaryKey(id);
        if (productImg != null) {

            return new ResultVO(ResStatus.OK, "success", productImg);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @Override
    public ResultVO modifyImg(ProductImg productImg) {
        productImg.setUpdatedTime(new Date());
        int i = productImgMapper.updateByPrimaryKeySelective(productImg);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "success", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @Override
    public ResultVO addImg(ProductImg productImg) {
        productImg.setCreatedTime(new Date());
        productImg.setUpdatedTime(new Date());
        productImg.setId(Integer.toString(new Random().nextInt(2147483646)+1));
        int i = productImgMapper.insert(productImg);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "SUCCESS", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @Override
    public ResultVO deleteImg(String id) {
        int i = productImgMapper.deleteByPrimaryKey(id);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "SUCCESS", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }
}

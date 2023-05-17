package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.ProductSkuMapper;
import com.zhangxu.agmall.entity.ProductSku;
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
public class ProductSkuServiceImpl implements ProductSkusService {
    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    public ResultVO selectOneSku(String skuId) {
        ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuId);
        if (productSku != null) {
            return new ResultVO(ResStatus.OK, "success", productSku);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @Override
    public ResultVO modifySku(ProductSku productSku) {
        int i = productSkuMapper.updateByPrimaryKeySelective(productSku);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "success", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }

    @Override
    public ResultVO addSku(ProductSku productSku) {
        productSku.setCreateTime(new Date());
        productSku.setUpdateTime(new Date());
        productSku.setSkuId(Integer.toString(new Random().nextInt(2147483646) + 1));
        int i = productSkuMapper.insert(productSku);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "SUCCESS", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }



    @Override
    public ResultVO deleteSku(String skuId) {
        int i = productSkuMapper.deleteByPrimaryKey(skuId);
        if (i > 0) {
            return new ResultVO(ResStatus.OK, "SUCCESS", null);
        }
        return new ResultVO(ResStatus.NO, "fail", null);
    }
}

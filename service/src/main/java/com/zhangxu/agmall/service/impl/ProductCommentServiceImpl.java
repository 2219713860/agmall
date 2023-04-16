package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.ProductCommentsMapper;
import com.zhangxu.agmall.entity.ProductComments;
import com.zhangxu.agmall.entity.ProductCommentsVO;
import com.zhangxu.agmall.service.ProductCommentService;
import com.zhangxu.agmall.utils.PageHelper;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.beans.PersistenceDelegate;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-14
 */
@Service
@Repository
public class ProductCommentServiceImpl implements ProductCommentService {

    @Autowired
    private ProductCommentsMapper productCommentsMapper;

    @Override
    public ResultVO listCommentsByproduct(String productId, int pageNum, int limit) {
//        List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId(productId);
//        评论数量
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId", productId);
        int count = productCommentsMapper.selectCountByExample(example);
//        总页数
        int pageCount = count % limit == 0 ? count / limit : count / limit + 1;
//        查询当前页码的评论x信息
        int start = (pageNum - 1) * limit;
        List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.selectCommontsByProductId(productId, start, limit);
        return new ResultVO(ResStatus.OK, "success", new PageHelper<ProductCommentsVO>(count, pageCount, productCommentsVOS));

    }

    @Override
    public ResultVO getCommentsCountByProductId(String productId) {
//        评论数
        Example example = new Example(ProductComments.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("productId",productId);
        int count = productCommentsMapper.selectCountByExample(example);
//        好评数
        criteria.andEqualTo("commType",1);
        int goodCount = productCommentsMapper.selectCountByExample(example);
//        中评数
        Example midExample = new Example(ProductComments.class);
        Example.Criteria midCriteria = midExample.createCriteria();
        midCriteria.andEqualTo("productId",productId);
        midCriteria.andEqualTo("commType",0);
        int midCount = productCommentsMapper.selectCountByExample(midExample);
//        差评数
        Example badExample = new Example(ProductComments.class);
        Example.Criteria badCriteria = badExample.createCriteria();
        badCriteria.andEqualTo("productId",productId);
        badCriteria.andEqualTo("commType",-1);
        int badCount = productCommentsMapper.selectCountByExample(badExample);
//        计算好评率
        double percent =( Double.parseDouble(goodCount+"")/Double.parseDouble(count+""))*100;
        String substringPercent = (percent + "").substring(0, (percent + "").lastIndexOf(".") + 3);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count",count);
        map.put("goodCount",goodCount);
        map.put("midCount",midCount);
        map.put("badCount",badCount);
        map.put("percent",substringPercent);
        return new ResultVO(ResStatus.OK,"success",map);
    }
}

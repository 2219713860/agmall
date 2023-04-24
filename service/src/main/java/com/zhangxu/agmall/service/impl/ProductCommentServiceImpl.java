package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.OrdersMapper;
import com.zhangxu.agmall.dao.ProductCommentsMapper;
import com.zhangxu.agmall.entity.Orders;
import com.zhangxu.agmall.entity.ProductComments;
import com.zhangxu.agmall.entity.ProductCommentsVO;
import com.zhangxu.agmall.service.OrderService;
import com.zhangxu.agmall.service.ProductCommentService;
import com.zhangxu.agmall.utils.PageHelper;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.beans.PersistenceDelegate;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author zhangxu
 * @create 2023-04-14
 */
@Service
@Repository
public class ProductCommentServiceImpl implements ProductCommentService {

    @Autowired
    private ProductCommentsMapper productCommentsMapper;
    @Autowired
    private OrdersMapper ordersMapper;

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
        criteria.andEqualTo("productId", productId);
        int count = productCommentsMapper.selectCountByExample(example);
//        好评数
        criteria.andEqualTo("commType", 1);
        int goodCount = productCommentsMapper.selectCountByExample(example);
//        中评数
        Example midExample = new Example(ProductComments.class);
        Example.Criteria midCriteria = midExample.createCriteria();
        midCriteria.andEqualTo("productId", productId);
        midCriteria.andEqualTo("commType", 0);
        int midCount = productCommentsMapper.selectCountByExample(midExample);
//        差评数
        Example badExample = new Example(ProductComments.class);
        Example.Criteria badCriteria = badExample.createCriteria();
        badCriteria.andEqualTo("productId", productId);
        badCriteria.andEqualTo("commType", -1);
        int badCount = productCommentsMapper.selectCountByExample(badExample);
//        计算好评率
        double percent = (Double.parseDouble(goodCount + "") / Double.parseDouble(count + "")) * 100;
        String substringPercent = (percent + "").substring(0, (percent + "").lastIndexOf(".") + 3);
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", count);
        map.put("goodCount", goodCount);
        map.put("midCount", midCount);
        map.put("badCount", badCount);
        map.put("percent", substringPercent);
        return new ResultVO(ResStatus.OK, "success", map);
    }
    @Transactional
    public ResultVO addCommentByOrderItemList(List<ProductComments> productCommentsList, String orderId) {
        int flat = 0;
        for (int i = 0; i < productCommentsList.size(); i++) {
//            1、将评论信息插入到数据库
            ProductComments productComments = productCommentsList.get(i);
            productComments.setCommId(new RandomString(8).toString());
            int insert = productCommentsMapper.insert(productComments);
            flat = flat + insert;
//            关联着进行订单的状态更新
//                前端通过其他的参数形式，进行传递订单order_id
            Orders orders = new Orders();
            orders.setOrderId(orderId);
            orders.setStatus("5");
            //               2、进行状态更新
            ordersMapper.updateByPrimaryKeySelective(orders);//根据订单
        }
        if (flat == productCommentsList.size()) {
            return new ResultVO(ResStatus.OK, "success", flat);
        } else if (flat > 0) {
            return new ResultVO(ResStatus.NO, "提交不完全", flat);
        } else {
            return new ResultVO(ResStatus.NO, "评论失败", flat);
        }
    }

}

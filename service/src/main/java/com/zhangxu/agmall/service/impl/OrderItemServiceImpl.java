package com.zhangxu.agmall.service.impl;

import com.zhangxu.agmall.dao.OrderItemMapper;
import com.zhangxu.agmall.entity.OrderItem;
import com.zhangxu.agmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

/**
 * @author zhangxu
 * @create 2023-05-13
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public int deleteOrderItemByOrderId(String orderId) {
        Example example = new Example(OrderItem.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("orderId", orderId);
        int i = orderItemMapper.deleteByExample(example);
        return i;
    }
}

package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.OrderItem;
import com.zhangxu.agmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemMapper extends GeneralDAO<OrderItem> {
 public List<OrderItem> listOrderItemsByOrderId(String orderId);
}
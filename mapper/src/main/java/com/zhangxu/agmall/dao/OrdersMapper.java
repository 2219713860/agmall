package com.zhangxu.agmall.dao;

import com.zhangxu.agmall.entity.Orders;
import com.zhangxu.agmall.entity.OrdersVO;
import com.zhangxu.agmall.general.GeneralDAO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrdersMapper extends GeneralDAO<Orders> {

    public List<OrdersVO> selectOrders(
            @Param("userId") String userId,
            @Param("status") String status,
            @Param("start") int start,
            @Param("limit") int limit);

    public int beforePayupdateByOrdersId(
            @Param("orderid") String orderid,
            @Param("status") String status,
            @Param("update_time") Date update_time,
            @Param("cancel_time") Date cancel_time,
            @Param("close_type") Integer close_type);
}
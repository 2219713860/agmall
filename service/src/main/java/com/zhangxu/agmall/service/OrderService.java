package com.zhangxu.agmall.service;

import com.zhangxu.agmall.entity.Orders;
import com.zhangxu.agmall.vo.ResultVO;

import java.sql.SQLException;
import java.util.Map;

public interface OrderService {

    public Map<String,String> addOrder(String cids, Orders order) throws SQLException;
//
    public int updateOrderStatus(String orderId, String status);
//
    public ResultVO getOrderById(String orderId);

    public void closeOrder(String orderId);

    public ResultVO listOrders(String userId,String status,int pageNum, int limit);

    /**
     * 在未支付下的订单取消
     * @param orderId
     * @return
     */
    public ResultVO cancelOrdersOnNoPay(String orderId) throws Exception;
    public ResultVO afterCancelPay(String orderId) throws Exception;

    public ResultVO updateOrdersToDeleteStatusOne(String orderId);
/**
 * 按照orderId查询订单以及订单快照信息
 */
    public ResultVO getOrderVOByOrderId(String orderId);
    public int deleteOrderByOrderId(String orderId);

}

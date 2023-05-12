package com.zhangxu.agmall.service.timingtask;

import com.github.wxpay.sdk.WXPay;
import com.zhangxu.agmall.dao.OrdersMapper;
import com.zhangxu.agmall.entity.Orders;
import com.zhangxu.agmall.entity.ProductSku;
import com.zhangxu.agmall.entity.ShoppingCartVO;
import com.zhangxu.agmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @author zhangxu
 * @create 2023-04-24
 */

@Component
public class OrderTimeOutCheckTask {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderService orderService;

    private WXPay wxPay = new WXPay(new MyPayConfig());

    @Scheduled(cron = "0/30 * * * * ?")
    @Transactional
    public void checkAndCloseOrder() {
        try {
            //1.查询超过30min订单状态依然为待支付状态的订单
            Example example = new Example(Orders.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("status", "1");
            Date time = new Date(System.currentTimeMillis() - 30 * 60 * 1000);
            criteria.andLessThan("createTime", time);
            List<Orders> orders = ordersMapper.selectByExample(example);

            //2.访问微信平台接口，确认当前订单最终的支付状态
            for (int i = 0; i < orders.size(); i++) {
                Orders order = orders.get(i);
                HashMap<String, String> params = new HashMap<>();
                params.put("out_trade_no", order.getOrderId());
                Map<String, String> resp = wxPay.orderQuery(params);
                if ("SUCCESS".equalsIgnoreCase(resp.get("trade_state"))) {
                    //2.1 如果订单已经支付，则修改订单状态为"代发货/已支付"  status = 2
                    Orders updateOrder = new Orders();
                    updateOrder.setOrderId(order.getOrderId());
                    updateOrder.setStatus("2");
                    int updateNum = ordersMapper.updateByPrimaryKeySelective(updateOrder);
                } else if ("NOTPAY".equalsIgnoreCase(resp.get("trade_state"))) {
                    //2.2查询订单是未支付的状态
                    //a、向微信发送关闭订单的请求，微信不返回数据
                    Map<String, String> stringStringMap = wxPay.closeOrder(params);
                    System.out.println(stringStringMap);
                    // b.系统执行，关闭订单
                    orderService.closeOrder(order.getOrderId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

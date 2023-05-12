package com.zhangxu.agmall.controller;

//import com.github.wxpay.sdk.WXPay;
//import com.zhangxu.agmall.service.timingtask.MyPayConfig;

import com.github.wxpay.sdk.WXPay;
import com.zhangxu.agmall.service.timingtask.MyPayConfig;
import com.zhangxu.agmall.entity.Orders;
import com.zhangxu.agmall.service.OrderService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Api(value = "提供订单相关的操作接口", tags = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResultVO add(String cids, @RequestBody Orders order, @RequestHeader("token") String Token) {
        System.out.println("进入订单添加====");
        Map<String, String> stringStringMap = null;
        ResultVO resultVO = null;
        try {
            stringStringMap = orderService.addOrder(cids, order);
            if (stringStringMap != null) {
                String orderId = stringStringMap.get("orderId");
                //设置当前订单信息
                HashMap<String, String> data = new HashMap<>();
                data.put("body", stringStringMap.get("productNames"));  //商品描述
                data.put("out_trade_no", orderId);               //使用当前用户订单的编号作为当前支付交易的交易号
                data.put("fee_type", "CNY");                     //支付币种
                //data.put("total_fee",order.getActualAmount()*100+"");          //支付金额
                data.put("total_fee", "1");
                data.put("trade_type", "NATIVE");                //交易类型
                data.put("notify_url", "www.biggou.top:8080/pay/wxCallback");           //设置支付完成时的回调方法接口

                //发送请求，获取响应
                //微信支付：申请支付连接
                WXPay wxPay = new WXPay(new MyPayConfig());
//                向微信发送请求，
                Map<String, String> resp = wxPay.unifiedOrder(data);
                System.out.println("向微信申请支付短连接成功，下面是微信支付短连接");
                System.out.println(resp);
                stringStringMap.put("payUrl", resp.get("code_url"));
                //orderInfo中包含：订单编号，购买的商品名称，支付链接
                resultVO = new ResultVO(ResStatus.OK, "提交订单成功！", stringStringMap);
            } else {
                resultVO = new ResultVO(ResStatus.NO, "提交订单失败！", null);
            }
        } catch (SQLException e) {
            resultVO = new ResultVO(ResStatus.NO, "提交订单失败！", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResultVO(ResStatus.OK, "提交订单成功！", stringStringMap);
    }

    @GetMapping("/status/{oid}")
    public ResultVO getOrderStatus(@PathVariable("oid") String orderId, @RequestHeader("token") String token) {
        ResultVO resultVO = orderService.getOrderById(orderId);
        return resultVO;
    }

    @GetMapping("/list")
    @ApiOperation("订单查询接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "userId", value = "用户ID", required = true),
            @ApiImplicitParam(dataType = "string", name = "status", value = "订单状态", required = false),
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "limit", value = "每页条数", required = true)
    })
    public ResultVO list(@RequestHeader("token") String token,
                         String userId,
                         String status,
                         int pageNum,
                         int limit) {
        ResultVO resultVO = orderService.listOrders(userId, status, pageNum, limit);
        return resultVO;
    }

    @PutMapping("/cancelOrder")
    public ResultVO cancelOrder(@RequestBody Map<String, String> map, @RequestHeader("token") String token) {
        ResultVO resultVO = null;
        String orderId = map.get("orderId");
        try {
            resultVO = orderService.cancelOrdersOnNoPay(orderId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultVO;
    }

    @PutMapping("/deleteStatusToOne")
    public ResultVO updateOrdersDeleteStatusToOne(@RequestBody Map<String, String> map, @RequestHeader("token") String token) {
        String orderId = map.get("orderId");
        ResultVO resultVO = orderService.updateOrdersToDeleteStatusOne(orderId);
        return resultVO;
    }
}

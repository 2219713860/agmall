package com.zhangxu.agmall.service.impl;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.zhangxu.agmall.dao.OrderItemMapper;
import com.zhangxu.agmall.dao.OrdersMapper;
import com.zhangxu.agmall.dao.ProductSkuMapper;
import com.zhangxu.agmall.dao.ShoppingCartMapper;
import com.zhangxu.agmall.entity.*;
import com.zhangxu.agmall.service.OrderService;
import com.zhangxu.agmall.service.timingtask.MyPayConfig;
import com.zhangxu.agmall.utils.PageHelper;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private ProductSkuMapper productSkuMapper;

    private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    /**
     * 保存订单业务
     */
    @Transactional
    public Map<String, String> addOrder(String cids, Orders order) throws SQLException {
        logger.info("add order begin...");
        Map<String, String> map = new HashMap<>();

        //1.校验库存：根据cids查询当前订单中关联的购物车记录详情（包括库存）
        String[] arr = cids.split(",");
        List<Integer> cidsList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            cidsList.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> list = shoppingCartMapper.selectShoppingCartByCids(cidsList);

        boolean f = true;
        String untitled = "";
        for (ShoppingCartVO sc : list) {
            if (Integer.parseInt(sc.getCartNum()) > sc.getSkuStock()) {
                f = false;
            }
            //获取所有商品名称，以,分割拼接成字符串
            untitled = untitled + sc.getProductName() + ",";
        }

        if (f) {
//            System.out.println("-----库存校验完成");
            logger.info("product stock is OK...");
            //2.保存订单
            order.setUntitled(untitled);
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            //todo
            order.setOrderFreight(new BigDecimal(0.01));
            order.setDeleteStatus(0);
            order.setStatus("1");
            //生成订单编号
            String orderId = UUID.randomUUID().toString().replace("-", "");
            order.setOrderId(orderId);
            int i = ordersMapper.insert(order);

            //3.生成商品快照
            for (ShoppingCartVO sc : list) {
                int cnum = Integer.parseInt(sc.getCartNum());
                String itemId = System.currentTimeMillis() + "" + (new Random().nextInt(89999) + 10000);
                OrderItem orderItem = new OrderItem(itemId, orderId, sc.getProductId(), sc.getProductName(), sc.getProductImg(), sc.getSkuId(), sc.getSkuName(), new BigDecimal(sc.getSellPrice()), cnum, new BigDecimal(Integer.parseInt(sc.getSellPrice()) * cnum), new Date(), new Date(), 0);
                orderItemMapper.insert(orderItem);
                //增加商品销量
            }

            //4.扣减库存：根据套餐ID修改套餐库存量
            HashMap<String, Integer> skuIdMapMutiNum = new HashMap<>();
            Set<String> strings = skuIdMapMutiNum.keySet();
            for (ShoppingCartVO sc : list) {
                String skuId = sc.getSkuId();
                skuIdMapMutiNum.put(skuId, sc.getSkuStock());
            }
            for (ShoppingCartVO sc : list) {
                String skuId = sc.getSkuId();
                skuIdMapMutiNum.put(skuId, skuIdMapMutiNum.get(skuId) - Integer.parseInt(sc.getCartNum()));
            }
            ProductSku productSku;
            for (String skuId : strings) {
                int newStock = skuIdMapMutiNum.get(skuId);
                productSku = new ProductSku();
                productSku.setSkuId(skuId);
                productSku.setStock(newStock);
                productSkuMapper.updateByPrimaryKeySelective(productSku);
            }
            //5.删除购物车：当购物车中的记录购买成功之后，购物车中对应做删除操作
            for (int cid : cidsList) {
                shoppingCartMapper.deleteByPrimaryKey(cid);
            }
            logger.info("add order finished...");
            map.put("orderId", orderId);
            map.put("productNames", untitled);
            return map;
        } else {
            //表示库存不足
            return null;
        }
    }

    public int updateOrderStatus(String orderId, String status) {
        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setStatus(status);
        int i = ordersMapper.updateByPrimaryKeySelective(orders);
        return i;
    }

    public ResultVO getOrderById(String orderId) {
        Orders order = ordersMapper.selectByPrimaryKey(orderId);
        return new ResultVO(ResStatus.OK, "sucesss", order.getStatus());
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void closeOrder(String orderId) {
        synchronized (this) {
            //  1.修改当前订单：status=6 已关闭  close_type=1 超时未支付
            Orders cancleOrder = new Orders();
            cancleOrder.setOrderId(orderId);
            cancleOrder.setStatus("6");  //设置订单状态为已关闭
            cancleOrder.setCloseType(1); //关闭类型：超时未支付
            ordersMapper.updateByPrimaryKeySelective(cancleOrder);

            //  2.还原库存：先根据当前订单编号查询商品快照（skuid  buy_count）--->修改product_sku
            Example example1 = new Example(OrderItem.class);
            Example.Criteria criteria1 = example1.createCriteria();
            criteria1.andEqualTo("orderId", orderId);
            List<OrderItem> orderItems = orderItemMapper.selectByExample(example1);
            //还原库存
            for (int j = 0; j < orderItems.size(); j++) {
                OrderItem orderItem = orderItems.get(j);
                //修改
                ProductSku productSku = productSkuMapper.selectByPrimaryKey(orderItem.getSkuId());
                productSku.setStock(productSku.getStock() + orderItem.getBuyCounts());
                productSkuMapper.updateByPrimaryKey(productSku);
            }
        }
    }

    public ResultVO listOrders(String userId, String status, int pageNum, int limit) {
        //1.分页查询
        int start = (pageNum - 1) * limit;
        List<OrdersVO> ordersVOS = ordersMapper.selectOrders(userId, status, start, limit);
        //2.查询总记录数
        Example example = new Example(Orders.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andLike("userId", userId);
        if (status != null && !"".equals(status)) {
            criteria.andLike("status", status);
        }
        int count = ordersMapper.selectCountByExample(example);

        //3.计算总页数
        int pageCount = count % limit == 0 ? count / limit : count / limit + 1;

        //4.封装数据
        PageHelper<OrdersVO> pageHelper = new PageHelper<>(count, pageCount, ordersVOS);
        ;
        return new ResultVO(ResStatus.OK, "SUCCESS", pageHelper);
    }

    @Override
    @Transactional
    public ResultVO cancelOrdersOnNoPay(String orderId) throws Exception {
//        根据订单编号修改订单status为6：买家取消
        String status = "6";
        Date updateTime = new Date();
        Date cancelTime = new Date();
        int closeType = 4;
        int i = ordersMapper.beforePayupdateByOrdersId(orderId, status, updateTime, cancelTime, closeType);
        if (i < 1) {
            return new ResultVO(ResStatus.NO, "取消时，更新订单状态失败", null);
        }
//        根据订单ID查询订单快照表中的数据
        List<OrderItem> orderItems = orderItemMapper.listOrderItemsByOrderId(orderId);
//        查询到订单快照集合之后
        int flat = 0;//定义flat，
        for (OrderItem orderItem : orderItems) {
            //修改
            ProductSku productSku = productSkuMapper.selectByPrimaryKey(orderItem.getSkuId());
            productSku.setStock(productSku.getStock() + orderItem.getBuyCounts());
            int i1 = productSkuMapper.updateByPrimaryKey(productSku);
            flat = flat + i1;
        }
        if (flat < orderItems.size()) {
            return new ResultVO(ResStatus.NO, "取消订单，还原库存时，还原数量出错", null);
        }
//      向微信支付平台发送关闭订单请求，
//        编辑发送数据
        HashMap<String, String> data = new HashMap<>();
        data.put("out_trade_no", orderId);               //使用当前用户订单的编号作为当前支付交易的交易号
        WXPay wxPay = new WXPay(new MyPayConfig());
        Map<String, String> responseFromWxMap = wxPay.closeOrder(data);
        System.out.println(responseFromWxMap.toString());
//        WXPayUtil.
        if (responseFromWxMap != null && "success".equalsIgnoreCase(responseFromWxMap.get("result_code"))) {
            return new ResultVO(ResStatus.OK, "success", null);
        }
        return new ResultVO(ResStatus.NO, "微信支付平台发送关闭请求时，关闭订单失败", null);
    }

    @Override
    public ResultVO afterCancelPay(String orderId) throws Exception {

//        WXPayUtil.
        return null;
    }

    @Override
    public ResultVO updateOrdersToDeleteStatusOne(String orderId) {
        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setDeleteStatus(1);
        ordersMapper.updateByPrimaryKeySelective(orders);
        return new ResultVO(ResStatus.OK, "success", null);
    }

    @Override
    public ResultVO getOrderVOByOrderId(String orderId) {
        OrdersVO ordersVO = ordersMapper.selectOrderVOByOrderId(orderId);
        ArrayList<OrdersVO> ordersVOS = new ArrayList<>();
        if (ordersVO != null) {
            ordersVOS.add(ordersVO);
            return new ResultVO(ResStatus.OK, "success", ordersVOS);
        } else {
            return new ResultVO(ResStatus.NO, "查无此单", null);
        }
    }
    @Override
    public int deleteOrderByOrderId(String orderId) {
        int i = ordersMapper.deleteByPrimaryKey(orderId);
        return i;
    }

}

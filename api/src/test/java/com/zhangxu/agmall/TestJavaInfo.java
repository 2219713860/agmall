package com.zhangxu.agmall;

import com.zhangxu.agmall.dao.ShoppingCartMapper;
import com.zhangxu.agmall.entity.ShoppingCartVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author zhangxu
 * @create 2023-04-18
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestJavaInfo {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Test
    public void test() {
        List<Integer> integers = new ArrayList<>();
        integers.add(5);
        integers.add(17);
        List<ShoppingCartVO> shoppingCartVOlist = shoppingCartMapper.selectShoppingCartByCids(integers);
        for (ShoppingCartVO s : shoppingCartVOlist) {
            System.out.println(s);
        }
    }
}
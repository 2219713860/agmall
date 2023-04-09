package com.zhangxu.agmall.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.zhangxu.agmall.utils.Base64Utils;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.jsonwebtoken.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.ElementType;
import java.util.Date;

/**
 * @author zhangxu
 * @create 2023-04-08
 */
@RestController
@RequestMapping("/shopcart")
@CrossOrigin
@Api(value = "提供购物车业务相关接口", tags = "购物车管理")
public class ShopCartController {
    @GetMapping("/list")
    @ApiImplicitParam(dataType = "string", name = "token", value = "授权令牌", required = true)
    public ResultVO listCarts(String token) {
        if (token == null) {
            return new ResultVO(ResStatus.NO, "请先登录", null);
        } else {
            JwtParser parser = Jwts.parser();
            parser.setSigningKey("123456");

            Jws<Claims> claimsJws = null;
            try {
                claimsJws = parser.parseClaimsJws(token);
                Claims body = claimsJws.getBody();//获取token中用户数据
                String subject = body.getSubject();//从body中获取生成token时生成subject
                Object key1 = body.get("key1");
                if (key1 != null) {
                    System.out.println(key1);
                }
                ResultVO resultVO = new ResultVO(ResStatus.OK, "success", null);
                return resultVO;
            } catch (ExpiredJwtException e) {
                return new ResultVO(ResStatus.NO, "登陆已过期,请重新登录", null);
            } catch (UnsupportedJwtException e) {
                return new ResultVO(ResStatus.NO, "token不合法请自重", null);
            }
        }

    }
}


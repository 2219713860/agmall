package com.zhangxu.agmall.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author zhangxu
 * @create 2023-04-09
 */
@Component
public class CheckTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器生效了");
        String method = request.getMethod();
        System.out.println(method);
        if ("OPTIONS".equalsIgnoreCase(method)) {
            return true;
        }
        String token = request.getHeader("token");
        if (token.equalsIgnoreCase("null")) {
            ResultVO resultVO = new ResultVO(ResStatus.NOT_LOGIN, "Token为空，请先登录！！", null);
            this.doResponse(resultVO, response);
        } else {
//            如果没有异常，拦截器就不执行doresponse相应，并且放行请求；
            try {
                JwtParser parser = Jwts.parser();
                parser.setSigningKey("123456");
                parser.parseClaimsJws(token);
                return true;
            } catch (ExpiredJwtException e) {
                ResultVO resultVO = new ResultVO(ResStatus.LOGIN_FAIL, "登录过期，请重新登陆", null);
                this.doResponse(resultVO, response);
            } catch (UnsupportedJwtException e) {
                ResultVO resultVO = new ResultVO(ResStatus.TOKEN_ILLEGALITY, "token不合法请自重", null);
                doResponse(resultVO, response);
            } catch (Exception e) {
                ResultVO resultVO = new ResultVO(ResStatus.NOT_LOGIN, "请先登录", null);
                doResponse(resultVO, response);
            }
        }
        return true;
    }

    private void doResponse(ResultVO resultVO, HttpServletResponse response) throws IOException {
        String resultStr = new ObjectMapper().writeValueAsString(resultVO);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(resultStr);
        writer.flush();
        writer.close();
    }
}

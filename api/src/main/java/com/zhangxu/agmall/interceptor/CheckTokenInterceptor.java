package com.zhangxu.agmall.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
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
        if (token == null) {
            ResultVO resultVO = new ResultVO(ResStatus.NO, "请先登录！！", null);
            this.doResponse(resultVO, response);
        } else {

            try {
                JwtParser parser = Jwts.parser();
                parser.setSigningKey("123456");
                Jws<Claims> claimsJws = parser.parseClaimsJws(token);
                return true;
            } catch (ExpiredJwtException e) {
                ResultVO resultVO = new ResultVO(ResStatus.NO, "登录过期，请重新登陆", null);
                this.doResponse(resultVO, response);
            } catch (UnsupportedJwtException e) {
                ResultVO resultVO = new ResultVO(ResStatus.NO, "token不合法请自重", null);
                doResponse(resultVO, response);
            } catch (Exception e) {
                ResultVO resultVO = new ResultVO(ResStatus.NO, "token不合法请自重", null);
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

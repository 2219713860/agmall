package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.OrderService;
import com.zhangxu.agmall.service.UserService;
import com.zhangxu.agmall.vo.ResStatus;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author zhangxu
 * @create 2023-02-17
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{username}/{password}")
    public ResultVO getUser(@PathVariable("username") String username,
                            @PathVariable("password") String password) {
        ResultVO resultVO = userService.loginUser(username, password);
        return resultVO;
    }

    @PostMapping("/{username}/{password}")
    public ResultVO registUser(
            @PathVariable("username") String username,
            @PathVariable("password") String password
    ) {
        ResultVO resultVO = userService.registUser(username, password);
        return resultVO;
    }

    @ApiOperation("校验Token是否过期接口")
    @PostMapping("/check")
    public ResultVO userTokenCheck(@RequestHeader("token") String token) {
        return new ResultVO(ResStatus.OK,"SUCCESS",null);
    }
}

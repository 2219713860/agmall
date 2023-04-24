package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.UserService;
import com.zhangxu.agmall.vo.ResultVO;
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
}

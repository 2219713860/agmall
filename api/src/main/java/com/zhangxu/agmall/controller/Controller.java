package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.UserService;
import com.zhangxu.agmall.vo.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhangxu
 * @create 2023-02-17
 */
@RestController
@RequestMapping("/user")
class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{username}/{password}")
    public ResultVO getUser(@PathVariable("username")String username,
                            @PathVariable("password") String password
                            ) {
        ResultVO resultVO = userService.selectUser(username,password);
        return resultVO;
    }
}

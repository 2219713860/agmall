package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.service.UserService;
import com.zhangxu.agmall.service.entity.User;
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

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") String userId) {
        User user = userService.selectUser(userId);
        return user;
    }
}

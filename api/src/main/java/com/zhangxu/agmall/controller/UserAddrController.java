package com.zhangxu.agmall.controller;

import com.zhangxu.agmall.entity.UserAddr;
import com.zhangxu.agmall.service.UserAddrService;
import com.zhangxu.agmall.vo.ResultVO;
import com.zhangxu.agmall.service.UserAddrService;
import com.zhangxu.agmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Api(value = "提供收货地址相关接口",tags = "收货地址管理")
@RequestMapping("/useraddr")
public class UserAddrController {
    @Autowired
    private UserAddrService userAddrService;

    @GetMapping("/list")
    @ApiImplicitParam(dataType = "int",name = "userId", value = "用户ID",required = true)
    public ResultVO listAddr(Integer userId, @RequestHeader("token") String token){
        ResultVO resultVO = userAddrService.listUserAddrByUserId(userId);
        return resultVO;
    }
    @PutMapping ("/put")
    public ResultVO modifyAddr(@RequestBody UserAddr userAddr, @RequestHeader("token") String token){
        ResultVO resultVO = userAddrService.modifyAddrByAddrId(userAddr);
        return resultVO;
    }
    @DeleteMapping ("/delete")
    @ApiImplicitParam(dataType = "String",name = "addrId", value = "地址表ID",required = true)
    public ResultVO deleteAddr(String addrId, @RequestHeader("token") String token){
        ResultVO resultVO = userAddrService.deleteAddrByAddrId(addrId);
        return resultVO;
    }
    @PostMapping ("/add")
    public ResultVO addAddr(@RequestBody UserAddr userAddr, @RequestHeader("token") String token){
        ResultVO resultVO = userAddrService.AddrByAddrId(userAddr);
        return resultVO;
    }



}

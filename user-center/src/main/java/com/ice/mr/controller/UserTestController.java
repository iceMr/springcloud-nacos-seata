package com.ice.mr.controller;

import com.ice.mr.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Api(value = "UserTestController", description = "用户接口")
public class UserTestController {

    @Resource
    private UserService userService;


    @ApiOperation(value="扣除用户账户金额",notes="扣除用户账户金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户ID",dataType="String",required = true,paramType = "query"),
            @ApiImplicitParam(name="amount",value="扣除金额",dataType="double",required = true,paramType = "query"),
    })
    @PostMapping("/deductAccount")
    public Boolean deductAccount(@RequestParam(required = true) String  userId, @RequestParam(required = true) double amount ,
                                 HttpServletRequest request){
        return  userService.deductAccount(userId,amount);
    }




}

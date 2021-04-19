package com.ice.mr.controller;

import com.ice.mr.service.OrderService;
import ice.mr.pojo.UserOrder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
@Api(value = "OrderTestController", description = "订单测试接口")
public class OrderTestController {

    @Resource
    private OrderService orderService;


    @ApiOperation(value="添加用户订单",notes="添加用户订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userOrder",value="订单对象",dataType="UserOrder",required = true,paramType = "body"),
    })
    @PostMapping("/addOrder")
    public Boolean addOrder(@RequestBody UserOrder userOrder, HttpServletRequest request){
        return  orderService.addOrder(userOrder);
    }


}

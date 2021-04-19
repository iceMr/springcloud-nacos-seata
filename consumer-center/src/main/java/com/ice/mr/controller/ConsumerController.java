package com.ice.mr.controller;

import com.ice.mr.feign.OrderFeignClient;
import com.ice.mr.feign.UserFeignClient;
import com.ice.mr.interceptor.ResultResponseAnnotation;
import ice.mr.pojo.UserOrder;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/consumer")
@Api(value = "ConsumerController", description = "测试接口")
public class ConsumerController {

    @Resource
    private OrderFeignClient orderFeignClient;
    @Resource
    private UserFeignClient userFeignClient;

    @ApiOperation(value="测试SeaTac事务接口",notes="测试SeaTac事务接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name="rollbackFlag",value="是否回滚",dataType="String",required = false,paramType = "query"),
    })
    @GetMapping("/seataTest")
    @ResultResponseAnnotation
    @GlobalTransactional
    public boolean seataTest(@RequestParam(required = false) Boolean rollbackFlag, HttpServletRequest request) {

        System.out.println("开始全局事务，XID = " + RootContext.getXID());
        UserOrder userOrder =new UserOrder();
        userOrder.setAmount(100D);
        userOrder.setUserId("111");
        userOrder.setCommodityCode("abc");
        //增加订单
        orderFeignClient.addOrder(userOrder);
        //扣除账户资金
        userFeignClient.deductAccount(userOrder.getUserId(),userOrder.getAmount());
        if(rollbackFlag!=null && rollbackFlag){
            throw  new RuntimeException("手动抛出异常");
        }
        return true;
    }
}

package com.ice.mr.feign;

import ice.mr.pojo.UserOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-center")
public interface OrderFeignClient {

    @PostMapping("/order/addOrder")
    public Boolean addOrder(@RequestBody UserOrder userOrder);

}

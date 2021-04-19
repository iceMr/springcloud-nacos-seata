package com.ice.mr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "user-center")
public interface UserFeignClient {

    @PostMapping("/user/deductAccount")
    public Boolean deductAccount(@RequestParam(name="userId",required = true) String  userId, @RequestParam(name="amount",required = true) double amount);

}

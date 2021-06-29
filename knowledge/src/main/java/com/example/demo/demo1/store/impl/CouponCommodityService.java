package com.example.demo.demo1.store.impl;

import com.example.demo.demo1.store.ICommodity;
import com.example.demo.demo1.store.service.CouponService;

import java.util.Map;

/**
 * 优惠券
 */

public class CouponCommodityService  implements ICommodity {

    private CouponService couponService = new CouponService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {
        couponService.sendCoupon(uId, commodityId, bizId);
    }
}

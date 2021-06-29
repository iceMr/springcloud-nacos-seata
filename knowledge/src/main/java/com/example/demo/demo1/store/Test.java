package com.example.demo.demo1.store;

public class Test {


    public static void main(String[] args) throws Exception {
        StoreFactory storeFactory = new StoreFactory();
        // 1. 优惠券
        ICommodity commodityService_1 = storeFactory.getCommodityService(1);
        commodityService_1.sendCommodity("10001", "EGM1023938910232121323432","791098764902132", null);
        // 2. 实物商品
        ICommodity commodityService_2 = storeFactory.getCommodityService(2);
        commodityService_2.sendCommodity("10001", "EGM1023938910232121323432","791098764902132", null);

    }
}



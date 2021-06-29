package com.example.demo.demo1.store.impl;

import com.example.demo.demo1.store.ICommodity;
import com.example.demo.demo1.store.service.GoodsService;

import java.util.Map;

public class GoodsCommodityService  implements ICommodity {


    private GoodsService goodsService =new GoodsService();

    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {

        goodsService.sendGoods(uId, commodityId, bizId);
    }
}

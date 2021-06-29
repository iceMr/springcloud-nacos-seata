package com.example.demo.demo1.store.impl;

import com.example.demo.demo1.store.ICommodity;
import com.example.demo.demo1.store.service.CardService;

import java.util.Map;

public class CardCommodityService implements ICommodity {


    private CardService cardService =new CardService();


    @Override
    public void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception {

        cardService.snedCard(uId,commodityId,bizId);
    }
}

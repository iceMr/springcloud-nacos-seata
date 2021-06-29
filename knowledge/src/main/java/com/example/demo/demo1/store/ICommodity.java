package com.example.demo.demo1.store;

import java.util.Map;

/**
 * 发放接口
 */
public interface ICommodity {
    void sendCommodity(String uId, String commodityId, String bizId, Map<String, String> extMap) throws Exception;
}

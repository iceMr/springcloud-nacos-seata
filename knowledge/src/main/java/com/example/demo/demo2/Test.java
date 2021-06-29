package com.example.demo.demo2;

import com.example.demo.demo2.adapter.impl.EGMCacheAdapter;
import com.example.demo.demo2.adapter.impl.IIRCacheAdapter;
import com.example.demo.demo2.redis.CacheService;
import com.example.demo.demo2.redis.CacheServiceImpl;

public class Test {
    public static void main(String[] args) throws Exception {
        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01","⼩傅哥");
        proxy_EGM.get("user_name_01");
        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_01","⼩傅哥");
        proxy_IIR.get("user_name_01");
    }
}

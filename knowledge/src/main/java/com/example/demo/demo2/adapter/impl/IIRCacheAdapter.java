package com.example.demo.demo2.adapter.impl;

import com.example.demo.demo2.adapter.ICacheAdapter;
import com.example.demo.demo2.redis.IirRedis;

import java.util.concurrent.TimeUnit;

public class IIRCacheAdapter implements ICacheAdapter {


    private  IirRedis  iirRedis =new IirRedis();

    @Override
    public void get(String key) {
        iirRedis.get(key);
    }

    @Override
    public void set(String key, String value) {
        iirRedis.put(key,value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        iirRedis.put(key,value,timeout,timeUnit);
    }

    @Override
    public void del(String key) {
        iirRedis.del(key);
    }
}

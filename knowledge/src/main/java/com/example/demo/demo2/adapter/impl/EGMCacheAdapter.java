package com.example.demo.demo2.adapter.impl;

import com.example.demo.demo2.adapter.ICacheAdapter;
import com.example.demo.demo2.redis.EgmRedis;

import java.util.concurrent.TimeUnit;

public class EGMCacheAdapter  implements ICacheAdapter {

    private EgmRedis  egmRedis   =new EgmRedis();

    @Override
    public void get(String key) {
        egmRedis.get(key);
    }

    @Override
    public void set(String key, String value) {
        egmRedis.add(key,value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        egmRedis.add( key,  value,  timeout,  timeUnit);
    }

    @Override
    public void del(String key) {
        egmRedis.del(key);
    }
}

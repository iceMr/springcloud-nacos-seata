package com.example.demo.demo2.redis;

import java.util.concurrent.TimeUnit;

public class CacheServiceImpl  implements  CacheService {


    private  SimpleRedis  simpleRedis =new  SimpleRedis();


    @Override
    public void get(String key) {
        simpleRedis.get(key);
    }

    @Override
    public void set(String key, String value) {
        simpleRedis.set(key,value);
    }

    @Override
    public void set(String key, String value, long timeout, TimeUnit timeUnit) {
        simpleRedis.set( key,  value,  timeout,  timeUnit);
    }

    @Override
    public void del(String key) {
        simpleRedis.del(key);
    }
}

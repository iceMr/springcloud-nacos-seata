package com.example.demo.demo2.redis;

import java.util.concurrent.TimeUnit;

public class IirRedis {
    public void  get(String key){
        System.out.println("IirRedis获取");
    }
    public void  put(String key ,String value){
        System.out.println("IirRedis设置key与value");
    }
    public void  put(String key , String value , long timeout , TimeUnit timeUnit){
        System.out.println("IirRedis设置key与value,有超时系统");
    }
    public void  del(String key){
        System.out.println("EgmRedis删除");
    }
}

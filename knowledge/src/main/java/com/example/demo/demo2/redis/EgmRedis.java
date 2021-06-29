package com.example.demo.demo2.redis;

import java.util.concurrent.TimeUnit;

public class EgmRedis {

    public void  get(String key){
        System.out.println("EgmRedis获取");
    }
    public void  add(String key ,String value){
        System.out.println("EgmRedis设置key与value");
    }
    public void  add(String key , String value , long timeout , TimeUnit timeUnit){
        System.out.println("EgmRedis设置key与value,有超时系统");
    }
    public void  del(String key){
        System.out.println("EgmRedis删除");
    }
}

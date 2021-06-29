package com.example.demo.demo2.redis;

import java.util.concurrent.TimeUnit;

public class SimpleRedis {

    public void  get(String key){
        System.out.println("普通Redis获取");
    }
    public void  set(String key ,String value){
        System.out.println("普通Redis设置key与value");
    }
    public void  set(String key , String value , long timeout , TimeUnit timeUnit){
        System.out.println("普通Redis设置key与value,有超时系统");
    }
    public void  del(String key){
        System.out.println("普通Redis删除");
    }
}

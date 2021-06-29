package com.example.demo.demo2.adapter;

import java.util.concurrent.TimeUnit;

public interface ICacheAdapter {
    void get(String key) ;
    void set(String key, String value);
    void set(String key, String value, long timeout, TimeUnit timeUnit);
    void del(String key);
}

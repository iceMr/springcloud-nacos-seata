package com.example.demo.demo2;

import com.example.demo.ClassLoaderUtils;
import com.example.demo.demo2.adapter.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class JDKInvocationHandler implements InvocationHandler {

    private ICacheAdapter cacheAdapter;

    public JDKInvocationHandler(ICacheAdapter cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return ICacheAdapter.class.getMethod(method.getName(),
                ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter, args);
    }
}

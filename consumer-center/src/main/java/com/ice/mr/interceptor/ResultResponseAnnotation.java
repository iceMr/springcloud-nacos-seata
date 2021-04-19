package com.ice.mr.interceptor;


import java.lang.annotation.*;

/**
 * @author zhanghao
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ResultResponseAnnotation {
}

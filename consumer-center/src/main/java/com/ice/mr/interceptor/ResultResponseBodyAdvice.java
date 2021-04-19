
package com.ice.mr.interceptor;

import com.ice.mr.result.Result;
import com.ice.mr.result.ResultCodeEnum;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author zhanghao
 */
@Component
@ControllerAdvice
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        //不对类型判断
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(returnType.getMethod().isAnnotationPresent(ResultResponseAnnotation.class)){
            return new Result<Object>(ResultCodeEnum.DEFAULT_SUCCESS,body);
        }
        return body;
    }
}


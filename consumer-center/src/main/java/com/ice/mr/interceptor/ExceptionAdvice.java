package com.ice.mr.interceptor;

import com.ice.mr.result.Result;
import com.ice.mr.result.ResultCodeEnum;
import com.ice.mr.userDefinedException.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * 扫面应用层,所有的捕获方式,注意自定义异常抓取范围
 * @author Administrator
 */
@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger= LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler({Exception.class,Throwable.class})
    @ResponseBody
    public Result<Object> handlerException(HttpServletRequest request , Exception e, HttpServletResponse response){
        logger.error("通用拦截器拦截:",e);

        Result<Object> errorResult =new  Result<Object>(ResultCodeEnum.DEFAULT_FAILED,null);
        if(e instanceof ConstraintViolationException){
            //参数校验拦截异常
            String message = ((ConstraintViolationException) e).getConstraintViolations().iterator().next().getMessage();
            ResultCodeEnum resultCodeEnum =  ResultCodeEnum.VALIDATOR_FAILED;
            resultCodeEnum.setMessage(message);
            errorResult =new  Result<Object>(resultCodeEnum,null);
        }else if(e instanceof BusinessException){
            String   resultCode =	((BusinessException) e).getCode();
            if(StringUtils.isNotBlank(resultCode)){
                ResultCodeEnum resultCodeEnum = ResultCodeEnum.getEnum(resultCode);
                if(resultCodeEnum!=null){
                    errorResult =new  Result<Object>(resultCodeEnum,null);
                }
            }
        }
        return  errorResult;
    }
}

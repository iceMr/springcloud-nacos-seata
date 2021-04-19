package com.ice.mr.userDefinedException;


import com.ice.mr.result.ResultCodeEnum;

import java.io.Serializable;

/**
 * 通用业务异常类
 * @author zhanghao
 */
public class BusinessException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 3160241586346324994L;

    protected String code;

    public BusinessException() {}

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }
    public BusinessException(String code, String msgFormat, Object... args) {
        super(String.format(msgFormat, args));
        this.code = code;
    }
    public BusinessException(ResultCodeEnum resultCodeEnum, Object... args) {
        super(String.format(resultCodeEnum.message(), args));
        this.code = resultCodeEnum.code();
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}

package com.ice.mr.result;


import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author zhanghao
 */
public class Result<T> implements Serializable {

	@ApiModelProperty(value="code",hidden=false,notes="状态码")
	private String code;
	@ApiModelProperty(value="message",hidden=false,notes="消息")
	private String message;
	@ApiModelProperty(value="data",hidden=false,notes="数据体")
	private T data;
	public Result() {
		this.code=ResultCodeEnum.DEFAULT_SUCCESS.code();
		this.message=ResultCodeEnum.DEFAULT_SUCCESS.message();
	}

	public Result(ResultCodeEnum resultCodeEnum,T data) {
		this.code=resultCodeEnum.code();
		this.message=resultCodeEnum.message();
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}

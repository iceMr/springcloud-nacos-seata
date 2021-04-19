package com.ice.mr.interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Enumeration;

/**
 * @author Administrator
 */
@Aspect
@Component
@Order(2)
public class CommonInterceptor {

	private static final Logger logger= LoggerFactory.getLogger(CommonInterceptor.class);


	@Pointcut(value="execution(* com.ice..*Controller.*(..))")
	public void pointCut(){}

	/**
	 * 前置切点
	 * @param joinPoint  切入对象
	 */
	@Before(value="pointCut()")
	public void before(JoinPoint joinPoint ) throws Throwable  {
		RequestAttributes ras = RequestContextHolder.getRequestAttributes();
		if(ras instanceof ServletRequestAttributes ){
			ServletRequestAttributes servletRequestAttributes=(ServletRequestAttributes)ras;
			HttpServletRequest request = servletRequestAttributes.getRequest();
		    this.recordMessage(joinPoint, request);
		}

	}

	/**
	 * 请求完成是返回信息
	 * @param result       返回数据对象
	 */
	@AfterReturning(returning = "result", pointcut = "pointCut()")
	public void afterReturning(Object result){
		logger.info("接口的返回值:" +JSON.toJSONString(result));
	}

	/**
	 * 后置异常通知
	 * 会监听整个切入面中产生的异常,
	 * 待会需要判断一下,被 around try catch 住的异常能否在这里被抓住,
	 * 以便确定怎么正确的返回异常信息.
	 * @param joinPoint   切入点对象
	 */
	@AfterThrowing(pointcut = "pointCut()",throwing = "e")
	public void afterThrowing(JoinPoint joinPoint ,Throwable e){
	}

	/**
	 * 后置最终通知,final增强，
	 * 不管是抛出异常或者正常退出都会执行
	 * @param jp   切入点对象
	 */
	@After("pointCut()")
	public void after(JoinPoint jp){
	}


	/**
	 * 环绕切点
	 * @param joinPoint 切入点对象
	 */
	@Around(value="pointCut()")
	public Object proceed(ProceedingJoinPoint joinPoint) throws  Throwable {
		return  joinPoint.proceed();
	}

	/**
	 * 认证--安全--记录
	 * 记录用户信息--- 装配Session
	 * 认证拦截..自定义细粒度权限拦截
	 * 参数校验 防止 Xss 攻击!
	 */
	private  void  recordMessage(JoinPoint joinPoint ,HttpServletRequest request) throws Throwable{
		//请求对象
		JSONObject  param = new JSONObject();
		//获取请求地址
		String requestURL =  request.getRequestURL().toString();
		String requestMethod= request.getMethod();
		Enumeration<String> headerNames = request.getHeaderNames();
		if (headerNames!=null){
			JSONObject  headerJson =new  JSONObject();
			while (headerNames.hasMoreElements()){
				String key = headerNames.nextElement();
				String value = request.getHeader(key);
				headerJson.put(key, value);
			}
			param.put("header", headerJson);
		}
		//ParameterName类型的
		Enumeration<String> parameterNames =  request.getParameterNames();
		if (parameterNames!=null){
			JSONObject  parameterJson =new  JSONObject();
			while (parameterNames.hasMoreElements()){
				String key = parameterNames.nextElement();
				String value = request.getParameter(key);
				parameterJson.put(key, value);
			}
			param.put("parameter", parameterJson);
		}
		//此处我们要获取body体中的参数
		Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
		Object[] args = joinPoint.getArgs();
		//参数级别的注释
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();

		for (int argIndex = 0; argIndex < args.length; argIndex++) {
			for (Annotation paramAnnotation : parameterAnnotations[argIndex]) {
				if (paramAnnotation instanceof RequestBody) {
					Object obj = args[argIndex];
					param.put("body", JSONObject.toJSONString(obj));
				}
			}
		}
		logger.info("\r\n调用方法名:"+method.getName());
		logger.info("\r\n请求地址:" + requestURL + "类型:" + requestMethod + "\r\n参数:" + param.toString());
	}

}


package com.ice.mr.util;
import java.util.regex.Pattern;

/**
 * 校验对象
 */
public class RegexUtilTools {
	
    /**
     * 正则表达式:手机号码
     */
    private  static final String REGEX_MOBILE = "^1(3[0-9]|4[5,6,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8]|8[0-9]|9[1,6,8,9])\\d{8}$";
    
    /**
     * 正则表达式:验证邮箱
     */
	private  static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
   
    /**
     * 正则表达式：验证身份证 包含15位于18身份证
     */
	private static final String REGEX_ID_CARD18 = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Za-z])$";
	private static final String REGEX_ID_CARD15 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";

    /**
     *正则表达式：校验登录名强度校验  5到10位（字母开头 + 数字/字母/下划线）
     */
	private static final String REGEX_USERNAME_STRONG = "^[A-Za-z][A-Za-z0-9_-]{4,20}$";

	/**
	 *正则表达式：校验登录密码强度校验 必填字母数字及特殊字符，且以字母开头，6位以上
	 */
	private static final String REGEX_PASSWORD_STRONG = "^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{6,}$";



    /**
     * 判断手机号是否正确
     */
	public static boolean isMobile(String mobile) {
		   return Pattern.matches(REGEX_MOBILE, mobile);
    }

	/**
	 * 判断邮箱是否正确
	 */
	public static boolean isEmail(String email) {
		   return Pattern.matches(REGEX_EMAIL, email);
    }
	
	 /**
     * 校验身份证
     */
    public static boolean isIDCard(String idCard) {
    	boolean flag=false;
    	if(idCard.length()==15){
    		flag=Pattern.matches(REGEX_ID_CARD15, idCard);
    		return flag;
    	}else if(idCard.length()==18){
    		flag=Pattern.matches(REGEX_ID_CARD18, idCard);
    		return flag;
    	}else{
    		return false;
    	}
    }

	/**
	 * 校验登录账户名强度
	 */
	public static  boolean usernameStrong(String userName){
		return Pattern.matches(REGEX_USERNAME_STRONG, userName);
	}

	/**
	 * 校验密码强度
	 */
	public static  boolean passwordStrong(String password){
		return Pattern.matches(REGEX_PASSWORD_STRONG, password);
	}


}  